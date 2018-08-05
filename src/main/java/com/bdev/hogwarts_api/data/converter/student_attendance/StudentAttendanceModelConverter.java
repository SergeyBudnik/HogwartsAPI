package com.bdev.hogwarts_api.data.converter.student_attendance;

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel;

public class StudentAttendanceModelConverter {
    public static StudentAttendance convert(StudentAttendanceModel studentAttendanceModel) {
        return StudentAttendance
                .builder()
                .id(studentAttendanceModel.getId())
                .studentId(studentAttendanceModel.getStudentId())
                .time(studentAttendanceModel.getTime())
                .type(studentAttendanceModel.getType())
                .build();
    }
}
