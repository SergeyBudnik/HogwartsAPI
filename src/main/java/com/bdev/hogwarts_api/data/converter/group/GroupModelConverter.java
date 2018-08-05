package com.bdev.hogwarts_api.data.converter.group;

import com.bdev.hogwarts_api.data.dto.group.Group;
import com.bdev.hogwarts_api.data.dto.group.GroupLesson;
import com.bdev.hogwarts_api.data.model.group.GroupLessonModel;
import com.bdev.hogwarts_api.data.model.group.GroupModel;
import com.bdev.hogwarts_api.utils.EncodingUtils;

import java.util.stream.Collectors;

import static com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64;
import static java.util.stream.Collectors.toList;

public class GroupModelConverter {
    public static Group convert(GroupModel groupModel) {
        return Group.builder()
                .id(groupModel.getId())
                .cabinetId(groupModel.getCabinetId())
                .managerId(groupModel.getManagerId())
                .bookName(fromBase64(groupModel.getBookName()))
                .type(groupModel.getType())
                .lessons(groupModel.getLessons().stream().map(GroupModelConverter::convertLesson).collect(toList()))
                .age(groupModel.getAge())
                .educationLevel(groupModel.getEducationLevel())
                .color(groupModel.getColor())
                .build();
    }

    private static GroupLesson convertLesson(GroupLessonModel groupLessonModel) {
        return GroupLesson.builder()
                .id(groupLessonModel.getId())
                .teacherId(groupLessonModel.getTeacherId())
                .day(groupLessonModel.getDay())
                .startTime(groupLessonModel.getStartTime())
                .finishTime(groupLessonModel.getFinishTime())
                .build();
    }
}
