package com.bdev.hogwarts_api.data.converter.group

import com.bdev.hogwarts_api.data.dto.group.Group
import com.bdev.hogwarts_api.data.dto.group.GroupLesson
import com.bdev.hogwarts_api.data.model.group.GroupLessonModel
import com.bdev.hogwarts_api.data.model.group.GroupModel
import com.bdev.hogwarts_api.utils.EncodingUtils.toBase64
import java.util.*

object GroupDtoConverter {
    fun convert(group: Group): GroupModel {
        val groupModel = GroupModel()

        groupModel.id = group.id
        groupModel.cabinetId = group.cabinetId
        groupModel.managerId = group.managerId
        groupModel.headTeacherLogin = group.headTeacherLogin
        groupModel.bookName = toBase64(group.bookName)
        groupModel.type = group.type
        groupModel.lessons = group.lessons.map { it -> convertLesson(groupModel, it) }.toMutableList()
        groupModel.age = group.age
        groupModel.educationLevel = group.educationLevel
        groupModel.color = group.color

        return groupModel
    }

    private fun convertLesson(groupModel: GroupModel, lesson: GroupLesson): GroupLessonModel {
        val groupLessonModel = GroupLessonModel()

        groupLessonModel.id = lesson.id
        groupLessonModel.group = groupModel
        groupLessonModel.teacherId = lesson.teacherId
        groupLessonModel.teacherLogin = lesson.teacherLogin
        groupLessonModel.day = lesson.day
        groupLessonModel.startTime = lesson.startTime
        groupLessonModel.finishTime = lesson.finishTime
        groupLessonModel.creationTime = if (lesson.id == null) { Date().time } else { lesson.creationTime }
        groupLessonModel.deactivationTime = lesson.deactivationTime

        return groupLessonModel
    }
}
