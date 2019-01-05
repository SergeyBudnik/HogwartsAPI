package com.bdev.hogwarts_api.data.model.student_attendance

import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceType
import lombok.Data

import javax.persistence.*

@Data
@Entity
@Table(name = "HG_STUDENT_ATTENDANCE")
open class StudentAttendanceModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "STUDENT_ID")
    var studentId: Long? = null
    @Column(name = "ATTENDANCE_TYPE")
    @Enumerated(EnumType.STRING)
    var type: StudentAttendanceType? = null
    @Column(name = "ATTENDANCE_TIME")
    var time: Long? = null
}
