package com.bdev.hogwarts_api.data.converter.group;

import com.bdev.hogwarts_api.data.dto.group.Group;
import com.bdev.hogwarts_api.data.dto.group.GroupLesson;
import com.bdev.hogwarts_api.data.model.group.GroupLessonModel;
import com.bdev.hogwarts_api.data.model.group.GroupModel;
import com.bdev.hogwarts_api.utils.EncodingUtils;

import java.util.stream.Collectors;

import static com.bdev.hogwarts_api.utils.EncodingUtils.toBase64;
import static java.util.stream.Collectors.toList;

public class GroupDtoConverter {
    public static GroupModel convert(Group group) {
        GroupModel groupModel = new GroupModel();

        groupModel.setId(group.getId());
        groupModel.setCabinetId(group.getCabinetId());
        groupModel.setName(toBase64(group.getName()));
        groupModel.setBookName(toBase64(group.getBookName()));
        groupModel.setLessons(group.getLessons().stream().map(it -> convertLesson(groupModel, it)).collect(toList()));
        groupModel.setAge(group.getAge());
        groupModel.setEducationLevel(group.getEducationLevel());
        groupModel.setColor(group.getColor());

        return groupModel;
    }

    private static GroupLessonModel convertLesson(GroupModel groupModel, GroupLesson lesson) {
        GroupLessonModel groupLessonModel = new GroupLessonModel();

        groupLessonModel.setId(lesson.getId());
        groupLessonModel.setGroup(groupModel);
        groupLessonModel.setTeacherId(lesson.getTeacherId());
        groupLessonModel.setDay(lesson.getDay());
        groupLessonModel.setStartTime(lesson.getStartTime());
        groupLessonModel.setFinishTime(lesson.getFinishTime());

        return groupLessonModel;
    }
}
