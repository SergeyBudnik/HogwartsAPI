package com.bdev.hogwarts_api.data.model.student_attendance

import com.bdev.hogwarts_api.data.dto.group.GroupType
import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceType
import java.io.Serializable
import javax.persistence.*

@Embeddable
data class StudentAttendanceModelId(
        @Column(name = "STUDENT_LOGIN")
        val studentLogin: String,
        @Column(name = "ATTENDANCE_START_TIME")
        val startTime: Long,
        @Column(name = "ATTENDANCE_FINISH_TIME")
        val finishTime: Long
) : Serializable {
    @Suppress("unused")
    constructor(): this(
            studentLogin = "",
            startTime = 0L,
            finishTime = 0L
    )
}

@Entity
@Table(name = "HG_STUDENT_ATTENDANCE")
data class StudentAttendanceModel(
        @EmbeddedId
        val id: StudentAttendanceModelId,

        @Column(name = "ATTENDANCE_TYPE")
        @Enumerated(EnumType.STRING)
        val type: StudentAttendanceType,
        @Column(name = "GROUP_TYPE")
        @Enumerated(EnumType.STRING)
        val groupType: GroupType,
        @Column(name = "STUDENTS_IN_GROUP")
        val studentsInGroup: Int
) {
    @Suppress("unused")
    constructor(): this(
            id = StudentAttendanceModelId(),
            type = StudentAttendanceType.VISITED,
            groupType = GroupType.GROUP,
            studentsInGroup = 0
    )
}