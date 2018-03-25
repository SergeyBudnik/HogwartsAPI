package com.bdev.hogwarts_api.data.converter.student_status;

import com.bdev.hogwarts_api.data.dto.student.StudentStatus;
import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel;

public class StudentStatusModelConverter {
    public static StudentStatus convert(StudentStatusModel studentStatusModel) {
        return StudentStatus.builder()
                .id(studentStatusModel.getId())
                .studentId(studentStatusModel.getStudentId())
                .status(studentStatusModel.getStatus())
                .creationTime(studentStatusModel.getCreationTime())
                .build();
    }
}
