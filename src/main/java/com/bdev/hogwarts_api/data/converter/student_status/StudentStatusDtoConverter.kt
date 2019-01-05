package com.bdev.hogwarts_api.data.converter.student_status

import com.bdev.hogwarts_api.data.dto.student.StudentStatus
import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel

object StudentStatusDtoConverter {
    fun convert(studentStatus: StudentStatus): StudentStatusModel {
        val studentStatusModel = StudentStatusModel()

        studentStatusModel.id = studentStatus.id
        studentStatusModel.studentId = studentStatus.studentId
        studentStatusModel.status = studentStatus.status
        studentStatusModel.creationTime = studentStatus.creationTime
        studentStatusModel.actionTime = studentStatus.actionTime

        return studentStatusModel
    }
}
