package com.bdev.hogwarts_api.data.converter.student

import com.bdev.hogwarts_api.data.dto.student.Student
import com.bdev.hogwarts_api.data.model.student.StudentGroupReferenceModel
import com.bdev.hogwarts_api.data.model.student.StudentModel
import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel
import com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64
import java.util.stream.Collectors.toList

object StudentModelConverter {
    fun convert(studentModel: StudentModel, studentStatusModel: StudentStatusModel): Student {
        return Student(
                id = studentModel.id,
                groupIds = studentModel.groupIds.map { it.groupId },
                name = fromBase64(studentModel.name ?: throw RuntimeException()),
                statusType = studentStatusModel.status,
                phones = studentModel.phones.map { it -> fromBase64(it.value) },
                emails = studentModel.emails.map { it -> fromBase64(it.value) },
                educationLevel = studentModel.educationLevel,
                age = studentModel.age,
                referralSource = studentModel.referralSource
        )
    }
}
