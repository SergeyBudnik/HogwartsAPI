package com.bdev.hogwarts_api.data.converter.student_on_boarding

import com.bdev.hogwarts_api.data.dto.student.on_boarding.*
import com.bdev.hogwarts_api.data.model.student.on_boarding.StudentOnBoardingActionModel
import com.bdev.hogwarts_api.data.model.student.on_boarding.StudentOnBoardingModel
import com.bdev.hogwarts_api.utils.EncodingUtils
import java.util.*
import kotlin.collections.ArrayList

object StudentOnBoardingDtoConverter {
    fun convertNew(studentOnBoarding: NewStudentOnBoarding, personId: Long): StudentOnBoardingModel {
        val studentOnBoardingModel = StudentOnBoardingModel(
                login = studentOnBoarding.info.login,
                personId = personId,
                educationAge = studentOnBoarding.info.educationInfo.age.id,
                educationLevel = studentOnBoarding.info.educationInfo.level.id,
                managerLogin = studentOnBoarding.info.managerLogin,
                resultType = StudentOnBoardingResultType.PROGRESS.id,
                resultComment = null,
                actions = ArrayList()
        )

        studentOnBoardingModel.actions = listOf(
                convertNewAction(
                        studentOnBoardingModel = studentOnBoardingModel,
                        studentOnBoardingAction = studentOnBoarding.action
                )
        ).toMutableList()

        return studentOnBoardingModel
    }

    fun convertExisting(studentOnBoarding: ExistingStudentOnBoarding, personId: Long): StudentOnBoardingModel {
        val studentOnBoardingModel = StudentOnBoardingModel(
                login = studentOnBoarding.info.login,
                personId = personId,
                educationAge = studentOnBoarding.info.educationInfo.age.id,
                educationLevel = studentOnBoarding.info.educationInfo.level.id,
                managerLogin = studentOnBoarding.info.managerLogin,
                resultType = studentOnBoarding.result.type.id,
                resultComment = EncodingUtils.toBase64(studentOnBoarding.result.comment),
                actions = ArrayList()
        )

        studentOnBoardingModel.actions = studentOnBoarding.actions
                .map { convertExistingAction(
                        studentOnBoardingModel = studentOnBoardingModel,
                        studentOnBoardingAction = it
                ) }
                .toMutableList()

        return studentOnBoardingModel
    }

    private fun convertExistingAction(studentOnBoardingModel: StudentOnBoardingModel, studentOnBoardingAction: ExistingStudentOnBoardingAction): StudentOnBoardingActionModel {
        return StudentOnBoardingActionModel(
                studentOnBoarding = studentOnBoardingModel,
                assigneeLogin = studentOnBoardingAction.info.assigneeLogin,
                actionTime = studentOnBoardingAction.info.actionTime,
                description = EncodingUtils.toBase64(studentOnBoardingAction.info.description),
                creationTime = studentOnBoardingAction.creationTime,
                completionTime = studentOnBoardingAction.completionTime
        )
    }

    private fun convertNewAction(studentOnBoardingModel: StudentOnBoardingModel, studentOnBoardingAction: NewStudentOnBoardingAction): StudentOnBoardingActionModel {
        return StudentOnBoardingActionModel(
                studentOnBoarding = studentOnBoardingModel,
                assigneeLogin = studentOnBoardingAction.info.assigneeLogin,
                actionTime = studentOnBoardingAction.info.actionTime,
                description = EncodingUtils.toBase64(studentOnBoardingAction.info.description),
                creationTime = Date().time,
                completionTime = null
        )
    }
}
