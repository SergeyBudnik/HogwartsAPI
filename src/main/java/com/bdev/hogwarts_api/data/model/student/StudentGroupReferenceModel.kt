package com.bdev.hogwarts_api.data.model.student

import lombok.Data

import javax.persistence.*

@Data
@Entity
@Table(name = "HG_STUDENT_GROUP_REF")
open class StudentGroupReferenceModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    var student: StudentModel? = null
    @Column(name = "GROUP_ID", length = 256)
    var groupId: Long = 0L
}
