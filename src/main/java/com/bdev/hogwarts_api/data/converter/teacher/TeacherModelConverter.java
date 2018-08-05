package com.bdev.hogwarts_api.data.converter.teacher;

import com.bdev.hogwarts_api.data.dto.teacher.Teacher;
import com.bdev.hogwarts_api.data.model.teacher.TeacherEmailModel;
import com.bdev.hogwarts_api.data.model.teacher.TeacherModel;
import com.bdev.hogwarts_api.data.model.teacher.TeacherPhoneModel;
import com.bdev.hogwarts_api.utils.EncodingUtils;

import static com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64;
import static java.util.stream.Collectors.toList;

public class TeacherModelConverter {
    public static Teacher convert(TeacherModel teacherModel) {
        return Teacher.builder()
                .id(teacherModel.getId())
                .login(fromBase64(teacherModel.getLogin()))
                .name(fromBase64(teacherModel.getName()))
                .type(teacherModel.getType())
                .emails(teacherModel.getEmails().stream()
                        .map(it -> fromBase64(it.getValue()))
                        .collect(toList())
                )
                .phones(teacherModel.getPhones().stream()
                        .map(it -> fromBase64(it.getValue()))
                        .collect(toList())
                )
                .build();
    }
}
