package com.bdev.hogwarts_api.data.converter.student_on_boarding

import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.bdev.hogwarts_api.data.dto.education.EducationInfo
import com.bdev.hogwarts_api.data.dto.person.ExistingPersonInfo
import com.bdev.hogwarts_api.data.dto.student.on_boarding.*
import com.bdev.hogwarts_api.data.model.student.on_boarding.StudentOnBoardingActionModel
import com.bdev.hogwarts_api.data.model.student.on_boarding.StudentOnBoardingModel
import com.bdev.hogwarts_api.utils.EncodingUtils

object StudentOnBoardingModelConverter {
    fun convert(studentOnBoardingModel: StudentOnBoardingModel, person: ExistingPersonInfo): ExistingStudentOnBoarding {
        return ExistingStudentOnBoarding(
                info = StudentOnBoardingInfo(
                        login = studentOnBoardingModel.login,
                        person = person.person,
                        educationInfo = EducationInfo(
                                age = Age.fromId(studentOnBoardingModel.educationAge) ?: Age.UNKNOWN,
                                level = EducationLevel.fromId(studentOnBoardingModel.educationLevel) ?: EducationLevel.UNKNOWN
                        ),
                        managerLogin = studentOnBoardingModel.managerLogin
                ),
                actions = studentOnBoardingModel.actions.map { convertAction(it) },
                result = StudentOnBoardingResult(
                        type = StudentOnBoardingResultType.fromId(studentOnBoardingModel.resultType) ?: StudentOnBoardingResultType.PROGRESS,
                        comment = studentOnBoardingModel.resultComment?.let { EncodingUtils.fromBase64(it) } ?: ""
                )
        )
    }

    private fun convertAction(studentOnBoardingActionModel: StudentOnBoardingActionModel): ExistingStudentOnBoardingAction {
        return ExistingStudentOnBoardingAction(

                info = StudentOnBoardingActionInfo(
                        assigneeLogin = studentOnBoardingActionModel.assigneeLogin,
                        actionTime = studentOnBoardingActionModel.actionTime,
                        description = EncodingUtils.fromBase64(studentOnBoardingActionModel.description)
                ),
                creationTime = studentOnBoardingActionModel.creationTime,
                completionTime = studentOnBoardingActionModel.completionTime
        )
    }
}
