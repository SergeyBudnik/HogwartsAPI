package com.bdev.hogwarts_api.rest_service.event_participants

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.events.EventParticipant
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.event_participant.EventParticipantService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class EventParticipantsRestServiceImpl : EventParticipantsRestService {
    @Autowired
    private lateinit var eventParticipantService: EventParticipantService

    @Transactional(readOnly = true)
    override fun getAllParticipants(userInfo: MunicipaliUserInfo): List<EventParticipant> {
        return eventParticipantService.getAllParticipants()
    }

    @Transactional(readOnly = true)
    override fun getEventParticipant(userInfo: MunicipaliUserInfo, eventParticipantId: Long): EventParticipant {
        return eventParticipantService
                .getEventParticipant(eventParticipantId)
                ?: throw HttpEntityNotFoundException("")
    }

    @Transactional(readOnly = true)
    override fun getEventParticipants(
            userInfo: MunicipaliUserInfo,
            eventId: Long
    ): List<EventParticipant> {
        return eventParticipantService.getAllEventParticipants(eventId)
    }

    @Transactional
    override fun createEventParticipant(
            userInfo: MunicipaliUserInfo,
            eventParticipant: EventParticipant
    ): Long {
        return eventParticipantService.createParticipant(eventParticipant)!!
    }

    @Transactional
    override fun editEventParticipant(
            userInfo: MunicipaliUserInfo,
            eventParticipant: EventParticipant
    ) {
        eventParticipantService.editParticipant(eventParticipant)
    }

    @Transactional
    override fun deleteEventParticipant(userInfo: MunicipaliUserInfo, eventParticipantId: Long) {
        eventParticipantService.deleteParticipant(eventParticipantId)
    }
}
