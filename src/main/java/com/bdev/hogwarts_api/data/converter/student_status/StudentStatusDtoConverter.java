package com.bdev.hogwarts_api.data.converter.student_status;

import com.bdev.hogwarts_api.data.dto.student.StudentStatus;
import com.bdev.hogwarts_api.data.model.student_status.StudentStatusModel;

public class StudentStatusDtoConverter {
    public static StudentStatusModel convert(StudentStatus studentStatus) {
        StudentStatusModel studentStatusModel = new StudentStatusModel();

        studentStatusModel.setId(studentStatus.getId());
        studentStatusModel.setStudentId(studentStatus.getStudentId());
        studentStatusModel.setStatus(studentStatus.getStatus());
        studentStatusModel.setCreationTime(studentStatus.getCreationTime());

        return studentStatusModel;
    }
}
