package com.bdev.hogwarts_api.rest_service.event_participants

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.events.EventParticipant

interface EventParticipantsRestService {
    fun getAllParticipants(userInfo: MunicipaliUserInfo): List<EventParticipant>
    fun getEventParticipant(userInfo: MunicipaliUserInfo, eventParticipantId: Long): EventParticipant
    fun getEventParticipants(userInfo: MunicipaliUserInfo, eventId: Long): List<EventParticipant>
    fun createEventParticipant(userInfo: MunicipaliUserInfo, eventParticipant: EventParticipant): Long
    fun editEventParticipant(userInfo: MunicipaliUserInfo, eventParticipant: EventParticipant)
    fun deleteEventParticipant(userInfo: MunicipaliUserInfo, eventParticipantId: Long)
}
