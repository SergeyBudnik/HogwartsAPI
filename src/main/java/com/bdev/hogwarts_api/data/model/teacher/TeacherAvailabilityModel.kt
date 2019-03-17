package com.bdev.hogwarts_api.data.model.teacher

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.common.LessonTime

import javax.persistence.*

@Entity
@Table(name = "HG_TEACHER_AVAILABILITY")
open class TeacherAvailabilityModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHER_ID", nullable = false)
    var teacher: TeacherModel? = null
    @Column(name = "DAY_OF_WEEK")
    @Enumerated(EnumType.STRING)
    var dayOfWeek: DayOfWeek? = null
    @Column(name = "AVAILABLE_TIME")
    @Enumerated(EnumType.STRING)
    var time: LessonTime? = null
}
