package com.bdev.hogwarts_api.data.model.teacher

import javax.persistence.*

@Entity
@Table(name = "HG_TEACHER_PHONE")
open class TeacherPhoneModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHER_ID", nullable = false)
    var teacher: TeacherModel? = null
    @Column(name = "VALUE", length = 256)
    var value: String? = null
}
