package com.bdev.hogwarts_api.data.converter.teacher;

import com.bdev.hogwarts_api.data.dto.teacher.Teacher;
import com.bdev.hogwarts_api.data.dto.teacher.TeacherAvailability;
import com.bdev.hogwarts_api.data.model.teacher.TeacherAvailabilityModel;
import com.bdev.hogwarts_api.data.model.teacher.TeacherEmailModel;
import com.bdev.hogwarts_api.data.model.teacher.TeacherModel;
import com.bdev.hogwarts_api.data.model.teacher.TeacherPhoneModel;

import static com.bdev.hogwarts_api.utils.EncodingUtils.toBase64;
import static java.util.stream.Collectors.toList;

public class TeacherDtoConverter {
    public static TeacherModel convert(Teacher teacher) {
        TeacherModel teacherModel = new TeacherModel();

        teacherModel.setId(teacher.getId());
        teacherModel.setLogin(toBase64(teacher.getLogin()));
        teacherModel.setName(toBase64(teacher.getName()));
        teacherModel.setType(teacher.getType());
        teacherModel.setEmails(teacher.getEmails().stream().map(it -> convertEmail(teacherModel, it)).collect(toList()));
        teacherModel.setPhones(teacher.getPhones().stream().map(it -> convertPhone(teacherModel, it)).collect(toList()));
        teacherModel.setAvailability(teacher.getAvailability().stream().map(it -> convertAvailability(teacherModel, it)).collect(toList()));

        return teacherModel;
    }

    private static TeacherEmailModel convertEmail(TeacherModel teacherModel, String email) {
        TeacherEmailModel teacherEmailModel = new TeacherEmailModel();

        teacherEmailModel.setTeacher(teacherModel);
        teacherEmailModel.setValue(toBase64(email));

        return teacherEmailModel;
    }

    private static TeacherPhoneModel convertPhone(TeacherModel teacherModel, String phone) {
        TeacherPhoneModel teacherPhoneModel = new TeacherPhoneModel();

        teacherPhoneModel.setTeacher(teacherModel);
        teacherPhoneModel.setValue(toBase64(phone));

        return teacherPhoneModel;
    }

    private static TeacherAvailabilityModel convertAvailability(TeacherModel teacherModel, TeacherAvailability availability) {
        TeacherAvailabilityModel teacherAvailabilityModel = new TeacherAvailabilityModel();

        teacherAvailabilityModel.setTeacher(teacherModel);
        teacherAvailabilityModel.setDayOfWeek(availability.getDayOfWeek());
        teacherAvailabilityModel.setTime(availability.getTime());

        return teacherAvailabilityModel;
    }
}
