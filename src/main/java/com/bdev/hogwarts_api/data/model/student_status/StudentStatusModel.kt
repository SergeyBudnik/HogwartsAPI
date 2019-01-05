package com.bdev.hogwarts_api.data.model.student_status

import com.bdev.hogwarts_api.data.dto.student.StudentStatusType
import lombok.Data

import javax.persistence.*

@Data
@Entity
@Table(name = "HG_STUDENT_STATUS")
open class StudentStatusModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "STUDENT_ID")
    var studentId: Long? = null
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    var status: StudentStatusType = StudentStatusType.STUDYING
    @Column(name = "CREATION_TIME")
    var creationTime: Long = 0L
    @Column(name = "ACTION_TIME")
    var actionTime: Long = 0L
}
