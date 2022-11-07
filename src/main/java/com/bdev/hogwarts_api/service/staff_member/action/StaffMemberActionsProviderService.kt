package com.bdev.hogwarts_api.service.staff_member.action

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.lesson_instance.LessonInstance
import com.bdev.hogwarts_api.data.dto.lesson_instance.LessonInstanceStatus
import com.bdev.hogwarts_api.data.dto.staff.StaffMember
import com.bdev.hogwarts_api.data.dto.staff_member.action.StaffMemberAction
import com.bdev.hogwarts_api.data.dto.staff_member.action.StaffMemberActionStatus
import com.bdev.hogwarts_api.data.dto.staff_member.action.StaffMemberActionType
import com.bdev.hogwarts_api.data.dto.time.Month
import com.bdev.hogwarts_api.service.lesson_instance.LessonInstanceProviderService
import com.bdev.hogwarts_api.service.staff.StaffMemberStorageService
import org.springframework.stereotype.Service

interface StaffMemberActionsProviderService {
    fun getWeekActions(
        staffMemberLogin: String,
        weekIndex: Int,
        month: Month,
        year: Int,
        calculationTime: Long
    ): Map<DayOfWeek, List<StaffMemberAction>>
}

@Service
class StaffMemberActionsProviderServiceImpl constructor(
    private val staffMemberStorageService: StaffMemberStorageService,
    private val lessonInstanceProviderService: LessonInstanceProviderService
) : StaffMemberActionsProviderService {
    override fun getWeekActions(
        staffMemberLogin: String,
        weekIndex: Int,
        month: Month,
        year: Int,
        calculationTime: Long
    ): Map<DayOfWeek, List<StaffMemberAction>> {
        val staffMember = staffMemberStorageService.getStaffMember(login = staffMemberLogin)

        return if (staffMember != null) {
            val staffMemberActions = HashMap<DayOfWeek, List<StaffMemberAction>>()

            val staffMemberWeekLessonsInstances = lessonInstanceProviderService.getStaffMemberWeekLessonsInstances(
                staffMemberLogin = staffMemberLogin,
                weekIndex = weekIndex,
                month = month,
                year = year,
                calculationTime = calculationTime
            )

            for (dayOfWeek in DayOfWeek.values()) {
                staffMemberWeekLessonsInstances[dayOfWeek]
                    ?.let { staffMemberDayLessonsInstances ->
                        staffMemberActions[dayOfWeek] = getDayActions(
                            staffMember = staffMember,
                            staffMemberDayLessonsInstances = staffMemberDayLessonsInstances
                        )
                    }
            }

            staffMemberActions
        } else {
            emptyMap()
        }
    }

    private fun getDayActions(
        staffMember: StaffMember,
        staffMemberDayLessonsInstances: List<LessonInstance>
    ): List<StaffMemberAction> {
        val staffMemberDayActions = ArrayList<StaffMemberAction>()

        var previousLessonInstance: LessonInstance? = null

        for (lessonInstance in staffMemberDayLessonsInstances) {
            val shouldDoTrip = shouldDoTrip(
                previousLessonInstance = previousLessonInstance,
                currentLessonInstance = lessonInstance
            )

            if (shouldDoTrip) {
                staffMemberDayActions.add(
                    buildStaffMemberRoadAction(
                        staffMember = staffMember,
                        lessonInstance = lessonInstance
                    )
                )
            }

            staffMemberDayActions.add(
                buildStaffMemberLessonAction(
                    staffMember = staffMember,
                    lessonInstance = lessonInstance
                )
            )

            previousLessonInstance = lessonInstance
        }

        return staffMemberDayActions
    }

    private fun buildStaffMemberRoadAction(
        staffMember: StaffMember,
        lessonInstance: LessonInstance
    ): StaffMemberAction {
        return StaffMemberAction(
            staffMemberLogin = staffMember.login,
            startTime = lessonInstance.startTime - 30 * 60 * 1000L,
            finishTime = lessonInstance.startTime,
            type = StaffMemberActionType.ROAD,
            price = staffMember.salaryIn30m,
            status = when (lessonInstance.status) {
                LessonInstanceStatus.FINISHED -> StaffMemberActionStatus.FINISHED
                else -> StaffMemberActionStatus.FUTURE
            }
        )
    }

    private fun buildStaffMemberLessonAction(
        staffMember: StaffMember,
        lessonInstance: LessonInstance
    ): StaffMemberAction {
        val durationIn30m = (lessonInstance.finishTime - lessonInstance.startTime) / (30 * 60 * 1000L)

        println("$durationIn30m")

        return StaffMemberAction(
            staffMemberLogin = staffMember.login,
            startTime = lessonInstance.startTime,
            finishTime = lessonInstance.finishTime,
            type = if (lessonInstance.lesson.isOnline) {
                StaffMemberActionType.LESSON_ONLINE
            } else {
                StaffMemberActionType.LESSON_CABINET
            },
            price = (1.5 * staffMember.salaryIn30m * durationIn30m).toInt(),
            status = when (lessonInstance.status) {
                LessonInstanceStatus.FINISHED -> StaffMemberActionStatus.FINISHED
                LessonInstanceStatus.CANCELED -> StaffMemberActionStatus.CANCELED
                LessonInstanceStatus.NOT_FILLED -> StaffMemberActionStatus.NOT_FILLED
                LessonInstanceStatus.FUTURE -> StaffMemberActionStatus.FUTURE
            }
        )
    }

    private fun shouldDoTrip(
        previousLessonInstance: LessonInstance?,
        currentLessonInstance: LessonInstance
    ): Boolean {
        return when {
            currentLessonInstance.status == LessonInstanceStatus.CANCELED -> {
                false
            }
            currentLessonInstance.lesson.isOnline -> {
                false
            }
            previousLessonInstance == null -> {
                true
            }
            previousLessonInstance.status == LessonInstanceStatus.CANCELED -> {
                true
            }
            else -> {
                val previousLessonFinishTime = previousLessonInstance.lesson.finishTime
                val currentLessonStartTime = currentLessonInstance.lesson.startTime

                val distanceBetweenLessonsIsMoreThanHour =
                    currentLessonStartTime.index - previousLessonFinishTime.index > 2

                distanceBetweenLessonsIsMoreThanHour
            }
        }
    }
}
