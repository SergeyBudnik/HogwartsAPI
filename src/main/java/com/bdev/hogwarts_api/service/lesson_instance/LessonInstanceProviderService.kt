package com.bdev.hogwarts_api.service.lesson_instance

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.common.LessonTime
import com.bdev.hogwarts_api.data.dto.group.Group
import com.bdev.hogwarts_api.data.dto.group.GroupAndLesson
import com.bdev.hogwarts_api.data.dto.group.GroupLesson
import com.bdev.hogwarts_api.data.dto.lesson.LessonStatus
import com.bdev.hogwarts_api.data.dto.lesson.LessonStatusType
import com.bdev.hogwarts_api.data.dto.lesson_instance.LessonInstance
import com.bdev.hogwarts_api.data.dto.lesson_instance.LessonInstanceStatus
import com.bdev.hogwarts_api.data.dto.lesson_instance.LessonInstanceTimings
import com.bdev.hogwarts_api.data.dto.student.studying.Student
import com.bdev.hogwarts_api.data.dto.time.Month
import com.bdev.hogwarts_api.service.group.GroupService
import com.bdev.hogwarts_api.service.lesson_status.LessonStatusService
import com.bdev.hogwarts_api.service.student.StudentStorageService
import com.bdev.hogwarts_api.service.student_attendance.StudentAttendanceService
import com.bdev.hogwarts_api.utils.CalendarUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

interface LessonInstanceProviderService {
    fun getStaffMemberWeekLessonsInstances(
        staffMemberLogin: String,
        weekIndex: Int,
        month: Month,
        year: Int,
        calculationTime: Long
    ): Map<DayOfWeek, List<LessonInstance>>
}

@Service
class LessonInstanceProviderServiceImpl @Autowired constructor(
    private val groupService: GroupService,
    private val lessonStatusService: LessonStatusService,
    private val studentAttendanceService: StudentAttendanceService,
    private val studentStorageService: StudentStorageService
): LessonInstanceProviderService {
    override fun getStaffMemberWeekLessonsInstances(
        staffMemberLogin: String,
        weekIndex: Int,
        month: Month,
        year: Int,
        calculationTime: Long
    ): Map<DayOfWeek, List<LessonInstance>> {
        val lessonsInstances = HashMap<DayOfWeek, List<LessonInstance>>()

        for (dayOfWeek in DayOfWeek.values()) {
            val lessonInstanceTimings = LessonInstanceTimings(
                dayOfWeek = dayOfWeek,
                weekIndex = weekIndex,
                month = month,
                year = year
            )

            val lessonInstanceMonth = getLessonInstanceMonth(lessonInstanceTimings = lessonInstanceTimings)

            lessonsInstances[dayOfWeek] = if (lessonInstanceMonth == month) {
                getStaffMemberDayLessonsInstances(
                    staffMemberLogin = staffMemberLogin,
                    lessonInstanceTimings = lessonInstanceTimings,
                    calculationTime = calculationTime
                )
            } else {
                emptyList()
            }
        }

        return lessonsInstances
    }

    private fun getLessonInstanceMonth(
        lessonInstanceTimings: LessonInstanceTimings
    ): Month {
        val calendar = CalendarUtils.getInstance()

        calendar.timeInMillis = getLessonInstanceTimingsTime(
            time = LessonTime.T_07_00,
            lessonInstanceTimings = lessonInstanceTimings
        )

        return getMonth(calendar = calendar)
    }

    private fun getStaffMemberDayLessonsInstances(
        staffMemberLogin: String,
        lessonInstanceTimings: LessonInstanceTimings,
        calculationTime: Long
    ): List<LessonInstance> {
        return getStaffMemberDayLessons(
            staffMemberLogin = staffMemberLogin,
            dayOfWeek = lessonInstanceTimings.dayOfWeek
        ).asSequence()
            .mapNotNull { groupAndLesson ->
                val lessonStartTime = getLessonInstanceStartTime(
                    lesson = groupAndLesson.lesson,
                    lessonInstanceTimings = lessonInstanceTimings
                )

                val lessonFinishTime = getLessonInstanceFinishTime(
                    lesson = groupAndLesson.lesson,
                    lessonInstanceTimings = lessonInstanceTimings
                )

                val creationTimeMatches = groupAndLesson.lesson.creationTime <= lessonStartTime
                val deactivationTimeMatches = lessonFinishTime <= groupAndLesson.lesson.deactivationTime

                if (creationTimeMatches && deactivationTimeMatches) {
                    val lessonAttendanceFilled = isLessonAttendanceFilled(
                        group = groupAndLesson.group,
                        lessonStartTime = lessonStartTime,
                        lessonFinishTime = lessonFinishTime
                    )

                    val lessonStatus = lessonStatusService.getLessonStatus(
                        lessonId = groupAndLesson.lesson.id!!,
                        lessonActionTime = lessonStartTime
                    )

                    LessonInstance(
                        lesson = groupAndLesson.lesson,
                        status = getLessonInstanceStatus(
                            lessonStatus = lessonStatus,
                            lessonAttendanceFilled = lessonAttendanceFilled,
                            lessonStartTime = lessonStartTime,
                            calculationTime = calculationTime
                        ),
                        startTime = lessonStartTime,
                        finishTime = lessonFinishTime
                    )
                } else {
                    null
                }
            }.sortedBy { lessonInstance ->
                lessonInstance.lesson.startTime
            }
            .sortedBy { lessonInstance  ->
                lessonInstance.lesson.day
            }.toList()
    }

    private fun getLessonInstanceStatus(
        lessonStatus: LessonStatus?,
        lessonAttendanceFilled: Boolean,
        lessonStartTime: Long,
        calculationTime: Long
    ): LessonInstanceStatus {
        return when {
            calculationTime < lessonStartTime -> {
                LessonInstanceStatus.FUTURE
            }
            !lessonAttendanceFilled -> {
                LessonInstanceStatus.NOT_FILLED
            }
            lessonStatus == null -> {
                LessonInstanceStatus.NOT_FILLED
            }
            lessonStatus.type == LessonStatusType.CANCELED -> {
                LessonInstanceStatus.CANCELED
            }
            lessonStatus.type == LessonStatusType.FINISHED -> {
                LessonInstanceStatus.FINISHED
            }
            else -> {
                LessonInstanceStatus.FUTURE
            }
        }
    }

    private fun getStaffMemberDayLessons(
        staffMemberLogin: String,
        dayOfWeek: DayOfWeek
    ): List<GroupAndLesson> {
        val lessons = ArrayList<GroupAndLesson>()

        groupService
            .getAllGroups()
            .forEach { group ->
                group.lessons
                    .filter { lesson ->
                        lesson.teacherLogin == staffMemberLogin
                    }.filter { lesson ->
                        lesson.day == dayOfWeek
                    }.forEach { lesson ->
                        lessons.add(
                            GroupAndLesson(
                                group = group,
                                lesson = lesson
                            )
                        )
                    }
            }

        return lessons
    }

    private fun isLessonAttendanceFilled(
        group: Group,
        lessonStartTime: Long,
        lessonFinishTime: Long
    ): Boolean {
        return getLessonStudents(
            group = group,
            startTime = lessonStartTime,
            finishTime = lessonFinishTime
        ).map { student ->
            studentAttendanceService.getStudentAttendance(
                studentLogin = student.login,
                lessonTime = lessonStartTime
            )
        }.all { studentAttendance ->
            studentAttendance != null
        }
    }

    private fun getLessonStudents(
        group: Group,
        startTime: Long,
        finishTime: Long
    ): List<Student> {
        return studentStorageService
            .getAll()
            .filter { student ->
                student.studentGroups.any { studentGroup ->
                    val groupMatches = studentGroup.groupId == group.id
                    val startTimeMatches = studentGroup.startTime <= startTime
                    val finishTimeMatches = studentGroup.finishTime == null || finishTime <= studentGroup.finishTime

                    groupMatches && startTimeMatches && finishTimeMatches
                }
            }
    }

    private fun getLessonInstanceStartTime(
        lesson: GroupLesson,
        lessonInstanceTimings: LessonInstanceTimings
    ): Long {
        return getLessonInstanceTimingsTime(
            time = lesson.startTime,
            lessonInstanceTimings = lessonInstanceTimings
        )
    }

    private fun getLessonInstanceFinishTime(
        lesson: GroupLesson,
        lessonInstanceTimings: LessonInstanceTimings
    ): Long {
        return getLessonInstanceTimingsTime(
            time = lesson.finishTime,
            lessonInstanceTimings = lessonInstanceTimings
        )
    }

    private fun getLessonInstanceTimingsTime(
        time: LessonTime,
        lessonInstanceTimings: LessonInstanceTimings
    ): Long {
        val dayInMills = 24 * 60 * 60 * 1000L

        val calendar = CalendarUtils.getInstance()

        calendar.set(Calendar.YEAR, lessonInstanceTimings.year)
        calendar.set(Calendar.MONTH, lessonInstanceTimings.month.calendarMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 0)

        for (i in 0 until lessonInstanceTimings.weekIndex) {
            calendar.timeInMillis += (7 - getDayOfWeek(calendar = calendar).index) * dayInMills
        }

        calendar.timeInMillis +=
            (lessonInstanceTimings.dayOfWeek.index - getDayOfWeek(calendar = calendar).index) * dayInMills

        calendar.set(Calendar.HOUR_OF_DAY, time.hour)
        calendar.set(Calendar.MINUTE, time.minutes)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
    }

    private fun getMonth(calendar: Calendar): Month {
        return Month.findByCalendarMonth(calendarMonth = calendar.get(Calendar.MONTH))!!
    }

    private fun getDayOfWeek(calendar: Calendar): DayOfWeek {
        return DayOfWeek.findByCalendarDayOfWeek(calendarDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK))!!
    }
}
