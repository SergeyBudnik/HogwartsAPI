package com.bdev.hogwarts_api.data.converter.student_attendance;

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;
import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel;

public class StudentAttendanceDtoConverter {
    public static StudentAttendanceModel convert(StudentAttendance studentAttendance) {
        StudentAttendanceModel studentAttendanceModel = new StudentAttendanceModel();

        studentAttendanceModel.setId(studentAttendance.getId());
        studentAttendanceModel.setStudentId(studentAttendance.getStudentId());
        studentAttendanceModel.setType(studentAttendance.getType());
        studentAttendanceModel.setTime(studentAttendance.getTime());

        return studentAttendanceModel;
    }
}
