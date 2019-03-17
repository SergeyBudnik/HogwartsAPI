package com.bdev.hogwarts_api.data.model.event_participant

import com.bdev.hogwarts_api.data.dto.events.EventParticipantStatus

import javax.persistence.*

@Entity
@Table(name = "HG_EVENT_PARTICIPANT")
open class EventParticipantModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "EVENT_ID")
    var eventId: Long? = null
    @Column(name = "NAME")
    var name: String? = null
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    var status: EventParticipantStatus? = null
    @Column(name = "PHONE")
    var phone: String? = null
    @Column(name = "ENLISTED")
    var enlisted: Boolean? = null
}
