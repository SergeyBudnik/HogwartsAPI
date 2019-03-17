package com.bdev.hogwarts_api.data.converter.student_status

import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel

object StudentStatusModelConverter {
    fun convert(studentStatusModel: StudentStatusModel): StudentStatus {
        return StudentStatus(
                id = studentStatusModel.id,
                studentId = studentStatusModel.studentId ?: throw RuntimeException(),
                status = studentStatusModel.status,
                creationTime = studentStatusModel.creationTime,
                actionTime = studentStatusModel.actionTime
        )
    }
}
