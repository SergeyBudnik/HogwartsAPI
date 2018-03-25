package com.bdev.hogwarts_api.data.converter.teacher;

import com.bdev.hogwarts_api.data.dto.teacher.Teacher;
import com.bdev.hogwarts_api.data.model.teacher.TeacherEmailModel;
import com.bdev.hogwarts_api.data.model.teacher.TeacherModel;
import com.bdev.hogwarts_api.data.model.teacher.TeacherPhoneModel;
import lombok.Data;

import java.util.stream.Collectors;

@Data
public class TeacherDtoConverter {
    public static TeacherModel convert(Teacher teacher) {
        TeacherModel teacherModel = new TeacherModel();

        teacherModel.setId(teacher.getId());
        teacherModel.setName(teacher.getName());
        teacherModel.setType(teacher.getType());
        teacherModel.setEmails(teacher.getEmails().stream().map(it -> convertEmail(teacherModel, it)).collect(Collectors.toList()));
        teacherModel.setPhones(teacher.getPhones().stream().map(it -> convertPhone(teacherModel, it)).collect(Collectors.toList()));

        return teacherModel;
    }

    private static TeacherEmailModel convertEmail(TeacherModel teacherModel, String email) {
        TeacherEmailModel teacherEmailModel = new TeacherEmailModel();

        teacherEmailModel.setTeacher(teacherModel);
        teacherEmailModel.setValue(email);

        return teacherEmailModel;
    }

    private static TeacherPhoneModel convertPhone(TeacherModel teacherModel, String phone) {
        TeacherPhoneModel teacherPhoneModel = new TeacherPhoneModel();

        teacherPhoneModel.setTeacher(teacherModel);
        teacherPhoneModel.setValue(phone);

        return teacherPhoneModel;
    }

}
