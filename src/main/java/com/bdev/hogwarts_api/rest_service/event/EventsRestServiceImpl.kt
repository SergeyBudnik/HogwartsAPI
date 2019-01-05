package com.bdev.hogwarts_api.rest_service.event

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.events.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class EventsRestServiceImpl : EventsRestService {
    @Autowired
    private lateinit var speakingClubService: EventService

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
}
