package com.bdev.hogwarts_api.data.model.event_participant;

import com.bdev.hogwarts_api.data.dto.events.EventParticipantStatus;
import com.bdev.hogwarts_api.data.dto.student.StudentReferralSource;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_EVENT_PARTICIPANT")
public class EventParticipantModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EVENT_ID")
    private Long eventId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private EventParticipantStatus status;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "REFERRAL_SOURCE")
    @Enumerated(EnumType.STRING)
    private StudentReferralSource referralSource;
}
