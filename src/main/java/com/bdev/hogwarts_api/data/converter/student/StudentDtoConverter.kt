package com.bdev.hogwarts_api.data.converter.student

import com.bdev.hogwarts_api.data.dto.student.Student
import com.bdev.hogwarts_api.data.model.student.StudentEmailModel
import com.bdev.hogwarts_api.data.model.student.StudentGroupReferenceModel
import com.bdev.hogwarts_api.data.model.student.StudentModel
import com.bdev.hogwarts_api.data.model.student.StudentPhoneModel
import com.bdev.hogwarts_api.utils.EncodingUtils.toBase64

object StudentDtoConverter {
    fun convert(student: Student): StudentModel {
        val studentModel = StudentModel()

        studentModel.id = student.id
        studentModel.groupIds = student.groupIds.map { it -> convertGroupIds(studentModel, it) }.toMutableList()
        studentModel.name = toBase64(student.name)
        studentModel.phones = student.phones.map { it -> convertPhone(studentModel, it) }.toMutableList()
        studentModel.emails = student.emails.map { it -> convertEmail(studentModel, it) }.toMutableList()
        studentModel.vkLink = student.vkLink
        studentModel.educationLevel = student.educationLevel
        studentModel.age = student.age

        return studentModel
    }

    private fun convertGroupIds(studentModel: StudentModel, groupId: Long): StudentGroupReferenceModel {
        val studentGroupReferenceModel = StudentGroupReferenceModel()

        studentGroupReferenceModel.groupId = groupId
        studentGroupReferenceModel.student = studentModel

        return studentGroupReferenceModel
    }

    private fun convertEmail(studentModel: StudentModel, email: String): StudentEmailModel {
        val studentEmailModel = StudentEmailModel()

        studentEmailModel.student = studentModel
        studentEmailModel.value = toBase64(email)

        return studentEmailModel
    }

    private fun convertPhone(studentModel: StudentModel, phone: String): StudentPhoneModel {
        val studentPhoneModel = StudentPhoneModel()

        studentPhoneModel.student = studentModel
        studentPhoneModel.value = toBase64(phone)

        return studentPhoneModel
    }
}
