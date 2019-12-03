package com.bdev.hogwarts_api.data.model.group

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek
import com.bdev.hogwarts_api.data.dto.common.LessonTime

import javax.persistence.*

@Entity
@Table(name = "HG_GROUP_LESSON")
open class GroupLessonModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", nullable = false)
    var group: GroupModel? = null
    @Column(name = "TEACHER_LOGIN")
    var teacherLogin: String? = null
    @Column(name = "DAY")
    @Enumerated(EnumType.STRING)
    var day: DayOfWeek? = null
    @Column(name = "START_TIME")
    @Enumerated(EnumType.STRING)
    var startTime: LessonTime? = null
    @Column(name = "FINISH_TIME")
    @Enumerated(EnumType.STRING)
    var finishTime: LessonTime? = null
    @Column(name = "CREATION_TIME")
    var creationTime: Long? = null
    @Column(name = "DEACTIVATION_TIME")
    var deactivationTime: Long? = null
}
