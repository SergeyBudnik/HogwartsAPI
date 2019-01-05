package com.bdev.hogwarts_api.data.converter.teacher

import com.bdev.hogwarts_api.data.dto.teacher.Teacher
import com.bdev.hogwarts_api.data.dto.teacher.TeacherAvailability
import com.bdev.hogwarts_api.data.model.teacher.TeacherModel
import com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64

object TeacherModelConverter {
    fun convert(teacherModel: TeacherModel): Teacher {
        return Teacher(
                id = teacherModel.id,
                login = fromBase64(teacherModel.login ?: throw RuntimeException()),
                name = fromBase64(teacherModel.name ?: throw RuntimeException()),
                type = teacherModel.type ?: throw RuntimeException(),
                emails = (teacherModel.emails ?: throw RuntimeException()).map { it -> fromBase64(it.value ?: throw RuntimeException()) },
                phones = (teacherModel.phones ?: throw RuntimeException()).map { it -> fromBase64(it.value ?: throw RuntimeException()) },
                availability = (teacherModel.availability ?: throw RuntimeException())
                        .map { it -> TeacherAvailability(
                                    dayOfWeek = it.dayOfWeek ?: throw RuntimeException(),
                                    time = it.time ?: throw RuntimeException()
                            )
                        }
                )
    }
}
