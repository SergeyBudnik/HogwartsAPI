package com.bdev.hogwarts_api.data.model.student.studying

import javax.persistence.*

@Entity
@Table(name = "HG_STUDENT_GROUP_REF_2")
class StudentGroupReferenceModel(
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_LOGIN", nullable = false)
    var student: StudentModel,
    @Column(name = "GROUP_ID", length = 256, nullable = false)
    var groupId: Long,
    @Column(name = "START_TIME", nullable = false)
    var startTime: Long,
    @Column(name = "FINISH_TIME", nullable = true)
    var finishTime: Long?
) {
    constructor(): this(
            student = StudentModel(),
            groupId = 0L,
            startTime = 0L,
            finishTime = null
    )
}
