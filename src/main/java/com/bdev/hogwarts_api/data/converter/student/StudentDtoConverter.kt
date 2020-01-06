package com.bdev.hogwarts_api.data.converter.student

import com.bdev.hogwarts_api.data.dto.student.StudentGroup
import com.bdev.hogwarts_api.data.dto.student.studying.Student
import com.bdev.hogwarts_api.data.model.student.studying.StudentGroupReferenceModel
import com.bdev.hogwarts_api.data.model.student.studying.StudentModel

object StudentDtoConverter {
    fun convert(student: Student, personId: Long): StudentModel {
        val model =  StudentModel(
                login = student.login,
                personId = personId,
                educationAge = student.educationInfo.age.id,
                educationLevel = student.educationInfo.level.id,
                statusType = student.statusType.id,
                studentGroups = ArrayList()
        )

        model.studentGroups = student.studentGroups.map { convertGroup(model, it) }.toMutableList()

        return model
    }

    private fun convertGroup(studentModel: StudentModel, studentGroup: StudentGroup): StudentGroupReferenceModel {
        return StudentGroupReferenceModel(
                student = studentModel,
                groupId = studentGroup.groupId,
                startTime = studentGroup.startTime,
                finishTime = studentGroup.finishTime
        )
    }
}
