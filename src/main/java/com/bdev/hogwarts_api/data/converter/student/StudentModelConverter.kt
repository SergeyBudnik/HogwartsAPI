package com.bdev.hogwarts_api.data.converter.student

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.bdev.hogwarts_api.data.dto.education.EducationInfo
import com.bdev.hogwarts_api.data.dto.person.Person
import com.bdev.hogwarts_api.data.dto.student.StudentGroup
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType
import com.bdev.hogwarts_api.data.dto.student.studying.Student
import com.bdev.hogwarts_api.data.model.student.studying.StudentGroupReferenceModel
import com.bdev.hogwarts_api.data.model.student.studying.StudentModel

object StudentModelConverter {
    fun convert(studentModel: StudentModel, person: Person): Student {
        return Student(
                login = studentModel.login,
                person = person,
                educationInfo = EducationInfo(
                        level = EducationLevel.fromId(studentModel.educationLevel) ?: EducationLevel.UNKNOWN,
                        age = Age.fromId(studentModel.educationAge  ) ?: Age.UNKNOWN
                ),
                studentGroups = studentModel.studentGroups.map { convertGroups(it) },
                statusType = StudentStatusType.fromId(studentModel.statusType) ?: StudentStatusType.STUDYING
        )
    }

    private fun convertGroups(studentGroupReferenceModel: StudentGroupReferenceModel): StudentGroup {
        return StudentGroup(
                groupId = studentGroupReferenceModel.groupId,
                startTime = studentGroupReferenceModel.startTime,
                finishTime = studentGroupReferenceModel.finishTime
        )
    }
}
