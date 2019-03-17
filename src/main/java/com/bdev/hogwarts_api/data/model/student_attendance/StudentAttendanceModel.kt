package com.bdev.hogwarts_api.data.model.student_attendance

import com.bdev.hogwarts_api.data.dto.group.GroupType
import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceType

import javax.persistence.*

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
    @Column(name = "GROUP_TYPE")
    @Enumerated(EnumType.STRING)
    var groupType: GroupType? = null
    @Column(name = "STUDENTS_IN_GROUP")
    var studentsInGroup: Int? = null
    @Column(name = "ATTENDANCE_START_TIME")
    var startTime: Long? = null
    @Column(name = "ATTENDANCE_FINISH_TIME")
    var finishTime: Long? = null
}
