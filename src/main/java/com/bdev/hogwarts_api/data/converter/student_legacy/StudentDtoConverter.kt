package com.bdev.hogwarts_api.data.converter.student_legacy

import com.bdev.hogwarts_api.data.dto.student.StudentLegacy
import com.bdev.hogwarts_api.data.dto.student.StudentGroup
import com.bdev.hogwarts_api.data.model.student.StudentEmailModelLegacy
import com.bdev.hogwarts_api.data.model.student.StudentGroupReferenceModelLegacy
import com.bdev.hogwarts_api.data.model.student.StudentModelLegacy
import com.bdev.hogwarts_api.data.model.student.StudentPhoneModelLegacy
import com.bdev.hogwarts_api.utils.EncodingUtils.toBase64

object StudentDtoConverter {
    fun convert(student: StudentLegacy): StudentModelLegacy {
        val studentModel = StudentModelLegacy()

        studentModel.id = student.id
        studentModel.studentGroups = student.studentGroups.map { it -> convertGroupIds(studentModel, it) }.toMutableList()
        studentModel.name = toBase64(student.name)
        studentModel.phones = student.phones.map { it -> convertPhone(studentModel, it) }.toMutableList()
        studentModel.emails = student.emails.map { it -> convertEmail(studentModel, it) }.toMutableList()
        studentModel.vkLink = student.vkLink
        studentModel.educationLevel = student.educationLevel
        studentModel.age = student.age

        return studentModel
    }

    private fun convertGroupIds(studentModel: StudentModelLegacy, studentGroup: StudentGroup): StudentGroupReferenceModelLegacy {
        val studentGroupReferenceModel = StudentGroupReferenceModelLegacy()

        studentGroupReferenceModel.student = studentModel
        studentGroupReferenceModel.groupId = studentGroup.groupId
        studentGroupReferenceModel.startTime = studentGroup.startTime
        studentGroupReferenceModel.finishTime = studentGroup.finishTime

        return studentGroupReferenceModel
    }

    private fun convertEmail(studentModel: StudentModelLegacy, email: String): StudentEmailModelLegacy {
        val studentEmailModel = StudentEmailModelLegacy()

        studentEmailModel.student = studentModel
        studentEmailModel.value = toBase64(email)

        return studentEmailModel
    }

    private fun convertPhone(studentModel: StudentModelLegacy, phone: String): StudentPhoneModelLegacy {
        val studentPhoneModel = StudentPhoneModelLegacy()

        studentPhoneModel.student = studentModel
        studentPhoneModel.value = toBase64(phone)

        return studentPhoneModel
    }
}
