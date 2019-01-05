package com.bdev.hogwarts_api.data.model.events

import com.bdev.hogwarts_api.data.dto.common.LessonTime
import com.bdev.hogwarts_api.data.dto.events.EventType
import lombok.Data

import javax.persistence.*

@Data
@Entity
@Table(name = "HG_EVENT")
open class EventModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "EVENT_TYPE")
    @Enumerated(EnumType.STRING)
    var eventType: EventType? = null
    @Column(name = "NAME")
    var name: String? = null
    @Column(name = "CABINET_ID")
    var cabinetId: Long? = null
    @Column(name = "TEACHER_ID")
    var teacherId: Long? = null
    @Column(name = "DATE")
    var date: Long? = null
    @Column(name = "START_TIME")
    @Enumerated(EnumType.STRING)
    var startTime: LessonTime? = null
    @Column(name = "FINISH_TIME")
    @Enumerated(EnumType.STRING)
    var finishTime: LessonTime? = null
}
