package com.bdev.hogwarts_api.data.dto.events

import com.bdev.hogwarts_api.data.dto.student.StudentReferralSource

class EventParticipant(
    var id: Long? = null,
    var eventId: Long = 0,
    var name: String = "",
    var status: EventParticipantStatus = EventParticipantStatus.REQUEST,
    var phone: String = "",
    var referralSource: StudentReferralSource = StudentReferralSource.UNKNOWN,
    var enlisted: Boolean = false
)