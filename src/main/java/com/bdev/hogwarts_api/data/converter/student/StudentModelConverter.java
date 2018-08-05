package com.bdev.hogwarts_api.data.converter.student;

import com.bdev.hogwarts_api.data.dto.student.Student;
import com.bdev.hogwarts_api.data.model.student.StudentEmailModel;
import com.bdev.hogwarts_api.data.model.student.StudentGroupReferenceModel;
import com.bdev.hogwarts_api.data.model.student.StudentModel;
import com.bdev.hogwarts_api.data.model.student.StudentPhoneModel;
import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel;
import com.bdev.hogwarts_api.utils.EncodingUtils;

import static com.bdev.hogwarts_api.data.dto.student.StudentStatusType.REQUEST;
import static com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64;
import static java.util.stream.Collectors.toList;

public class StudentModelConverter {
    public static Student convert(StudentModel studentModel, StudentStatusModel studentStatusModel) {
        return Student.builder()
                .id(studentModel.getId())
                .groupIds(studentModel.getGroupIds().stream().map(StudentGroupReferenceModel::getGroupId).collect(toList()))
                .name(fromBase64(studentModel.getName()))
                .statusType(studentStatusModel == null ? REQUEST : studentStatusModel.getStatus())
                .phones(studentModel.getPhones().stream()
                        .map(it -> fromBase64(it.getValue()))
                        .collect(toList())
                )
                .emails(studentModel.getEmails().stream()
                        .map(it -> fromBase64(it.getValue()))
                        .collect(toList())
                )
                .educationLevel(studentModel.getEducationLevel())
                .age(studentModel.getAge())
                .referralSource(studentModel.getReferralSource())
                .build();
    }
}
