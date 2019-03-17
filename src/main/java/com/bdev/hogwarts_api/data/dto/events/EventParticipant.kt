package com.bdev.hogwarts_api.data.dto.events

class EventParticipant(
    var id: Long? = null,
    var eventId: Long = 0,
    var name: String = "",
    var status: EventParticipantStatus = EventParticipantStatus.REQUEST,
    var phone: String = "",
    var enlisted: Boolean = false
)