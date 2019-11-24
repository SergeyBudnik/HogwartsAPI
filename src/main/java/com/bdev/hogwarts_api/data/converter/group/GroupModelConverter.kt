package com.bdev.hogwarts_api.data.converter.group

import com.bdev.hogwarts_api.data.dto.group.Group
import com.bdev.hogwarts_api.data.dto.group.GroupLesson
import com.bdev.hogwarts_api.data.model.group.GroupLessonModel
import com.bdev.hogwarts_api.data.model.group.GroupModel
import com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64

object GroupModelConverter {
    fun convert(groupModel: GroupModel): Group {
        return Group(
                id = groupModel.id,
                bookName = fromBase64(groupModel.bookName ?: ""),
                type = groupModel.type ?: throw RuntimeException(),
                cabinetId = groupModel.cabinetId ?: throw RuntimeException(),
                managerId = groupModel.managerId ?: throw RuntimeException(),
                headTeacherLogin = groupModel.headTeacherLogin ?: "",
                lessons = (groupModel.lessons ?: throw RuntimeException()).map { convertLesson(it) },
                age = groupModel.age ?: throw RuntimeException(),
                educationLevel = groupModel.educationLevel ?: throw RuntimeException(),
                color = groupModel.color ?: throw RuntimeException()
        )
    }

    private fun convertLesson(groupLessonModel: GroupLessonModel): GroupLesson {
        return GroupLesson(
                id = groupLessonModel.id,
                teacherId = groupLessonModel.teacherId ?: throw RuntimeException(),
                teacherLogin = groupLessonModel.teacherLogin ?: "",
                day = groupLessonModel.day ?: throw RuntimeException(),
                startTime = groupLessonModel.startTime ?: throw RuntimeException(),
                finishTime = groupLessonModel.finishTime  ?: throw RuntimeException(),
                creationTime = groupLessonModel.creationTime ?: -1,
                deactivationTime = groupLessonModel.deactivationTime
        )
    }
}
