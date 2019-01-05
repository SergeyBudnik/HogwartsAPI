package com.bdev.hogwarts_api.data.converter.teacher

import com.bdev.hogwarts_api.data.dto.teacher.Teacher
import com.bdev.hogwarts_api.data.dto.teacher.TeacherAvailability
import com.bdev.hogwarts_api.data.model.teacher.TeacherAvailabilityModel
import com.bdev.hogwarts_api.data.model.teacher.TeacherEmailModel
import com.bdev.hogwarts_api.data.model.teacher.TeacherModel
import com.bdev.hogwarts_api.data.model.teacher.TeacherPhoneModel

import com.bdev.hogwarts_api.utils.EncodingUtils.toBase64
import java.util.stream.Collectors.toList

object TeacherDtoConverter {
    fun convert(teacher: Teacher): TeacherModel {
        val teacherModel = TeacherModel()

        teacherModel.id = teacher.id
        teacherModel.login = toBase64(teacher.login)
        teacherModel.name = toBase64(teacher.name)
        teacherModel.type = teacher.type
        teacherModel.emails = teacher.emails.map { it -> convertEmail(teacherModel, it) }.toMutableList()
        teacherModel.phones = teacher.phones.map { it -> convertPhone(teacherModel, it) }.toMutableList()
        teacherModel.availability = teacher.availability.map { it -> convertAvailability(teacherModel, it) }.toMutableList()

        return teacherModel
    }

    private fun convertEmail(teacherModel: TeacherModel, email: String): TeacherEmailModel {
        val teacherEmailModel = TeacherEmailModel()

        teacherEmailModel.teacher = teacherModel
        teacherEmailModel.value = toBase64(email)

        return teacherEmailModel
    }

    private fun convertPhone(teacherModel: TeacherModel, phone: String): TeacherPhoneModel {
        val teacherPhoneModel = TeacherPhoneModel()

        teacherPhoneModel.teacher = teacherModel
        teacherPhoneModel.value = toBase64(phone)

        return teacherPhoneModel
    }

    private fun convertAvailability(teacherModel: TeacherModel, availability: TeacherAvailability): TeacherAvailabilityModel {
        val teacherAvailabilityModel = TeacherAvailabilityModel()

        teacherAvailabilityModel.teacher = teacherModel
        teacherAvailabilityModel.dayOfWeek = availability.dayOfWeek
        teacherAvailabilityModel.time = availability.time

        return teacherAvailabilityModel
    }
}
