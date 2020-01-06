package com.bdev.hogwarts_api.data.model.student

import javax.persistence.*

@Entity
@Table(name = "HG_STUDENT_PHONE")
open class StudentPhoneModelLegacy {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    var student: StudentModelLegacy? = null

    @Column(name = "VALUE", length = 256)
    var value: String = ""
}
