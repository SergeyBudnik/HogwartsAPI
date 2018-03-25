package com.bdev.hogwarts_api.data.converter.teacher;

import com.bdev.hogwarts_api.data.dto.teacher.Teacher;
import com.bdev.hogwarts_api.data.model.teacher.TeacherEmailModel;
import com.bdev.hogwarts_api.data.model.teacher.TeacherModel;
import com.bdev.hogwarts_api.data.model.teacher.TeacherPhoneModel;

import static java.util.stream.Collectors.toList;

public class TeacherModelConverter {
    public static Teacher convert(TeacherModel teacherModel) {
        return Teacher.builder()
                .id(teacherModel.getId())
                .name(teacherModel.getName())
                .type(teacherModel.getType())
                .emails(teacherModel.getEmails().stream().map(TeacherEmailModel::getValue).collect(toList()))
                .phones(teacherModel.getPhones().stream().map(TeacherPhoneModel::getValue).collect(toList()))
                .build();
    }
}
