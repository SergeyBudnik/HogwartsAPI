package com.bdev.hogwarts_api.data.converter.student

import com.bdev.hogwarts_api.data.dto.student.Student
import com.bdev.hogwarts_api.data.dto.student.StudentGroup
import com.bdev.hogwarts_api.data.model.student.StudentModel
import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel
import com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64

object StudentModelConverter {
    fun convert(studentModel: StudentModel, studentStatusModel: StudentStatusModel): Student {
        return Student(
                id = studentModel.id,
                studentGroups = studentModel.studentGroups.map { StudentGroup(
                        groupId = it.groupId,
                        startTime = it.startTime ?: throw RuntimeException(),
                        finishTime = it.finishTime
                ) },
                name = fromBase64(studentModel.name ?: throw RuntimeException()),
                statusType = studentStatusModel.status,
                phones = studentModel.phones.map { it -> fromBase64(it.value) },
                emails = studentModel.emails.map { it -> fromBase64(it.value) },
                vkLink = studentModel.vkLink ?: "",
                educationLevel = studentModel.educationLevel,
                age = studentModel.age
        )
    }
}
