package com.bdev.hogwarts_api.data.model.student

import javax.persistence.*

@Entity
@Table(name = "HG_STUDENT_EMAIL")
open class StudentEmailModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    var student: StudentModel? = null
    @Column(name = "VALUE", length = 256)
    var value: String = ""
}
