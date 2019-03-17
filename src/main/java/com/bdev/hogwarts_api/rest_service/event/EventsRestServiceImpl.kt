package com.bdev.hogwarts_api.rest_service.event

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.dto.events.EventType
import com.bdev.hogwarts_api.data.dto.group.GroupLesson
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.events.EventService
import com.bdev.hogwarts_api.service.group.GroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
open class EventsRestServiceImpl : EventsRestService {
    @Autowired
    private lateinit var speakingClubService: EventService

    @Autowired
    private lateinit var groupService: GroupService

    @Transactional(readOnly = true)
    override fun getAll(userInfo: MunicipaliUserInfo): List<Event> {
        return speakingClubService.getAll()
    }

    @Transactional(readOnly = true)
    override fun getById(userInfo: MunicipaliUserInfo, id: Long): Event {
        return speakingClubService.getById(id) ?:
                throw HttpEntityNotFoundException("Event with id '%d' does not exist", id)
    }

    @Transactional
    override fun create(userInfo: MunicipaliUserInfo, speakingClub: Event): Long {
        if (speakingClub.id != null) {
            throw HttpEntityIllegalStateException("Event id should be null during creation")
        }

        return speakingClubService.create(speakingClub)
    }

    @Transactional
    override fun update(userInfo: MunicipaliUserInfo, speakingClub: Event) {
        val speakingClubId = speakingClub.id ?:
                throw HttpEntityIllegalStateException("Event id should be non-null during edit")

        if (!speakingClubService.exists(speakingClubId)) {
            throw HttpEntityNotFoundException("Event with id '%d' does not exist", speakingClubId)
        }

        speakingClubService.update(speakingClub)
    }

    @Transactional
    override fun delete(userInfo: MunicipaliUserInfo, id: Long) {
        if (!speakingClubService.exists(id)) {
            throw HttpEntityNotFoundException("Event with id '%d' does not exist", id)
        }

        speakingClubService.delete(id)
    }

    @Transactional
    override fun generateWeekTests(userInfo: MunicipaliUserInfo) {
        val allEvents = speakingClubService.getAllFutureEvents(EventType.TEST_LESSON)

        groupService.getAllGroups().forEach { group ->
            group.lessons.forEach { lesson ->
                val lessonDate = getLessonDate(lesson)

                allEvents.find { it.date == lessonDate }
            } }
    }

    private fun getLessonDate(lesson: GroupLesson): Long {
        val calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT+3:00"))

        calendar.time = Date()

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        while (calendar.get(Calendar.DAY_OF_WEEK) != getCalendarDay(lesson.day)) {
            calendar.add(Calendar.DATE, 1)
        }

        return calendar.time.time
    }

    private fun getCalendarDay(dayOfWeek: DayOfWeek): Int {
        return when (dayOfWeek) {
            DayOfWeek.MONDAY -> Calendar.MONDAY
            DayOfWeek.TUESDAY -> Calendar.TUESDAY
            DayOfWeek.WEDNESDAY -> Calendar.WEDNESDAY
            DayOfWeek.THURSDAY -> Calendar.THURSDAY
            DayOfWeek.FRIDAY -> Calendar.FRIDAY
            DayOfWeek.SATURDAY -> Calendar.SATURDAY
            DayOfWeek.SUNDAY -> Calendar.SUNDAY
        }
    }
}
