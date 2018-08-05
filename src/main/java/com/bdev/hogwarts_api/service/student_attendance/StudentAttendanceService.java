package com.bdev.hogwarts_api.service.student_attendance;

import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;

import java.util.List;

public interface StudentAttendanceService {
    boolean exists(long attendanceId);
    List<StudentAttendance> getAllAttendances();
    List<StudentAttendance> getAttendances(long studentId);
    long addAttendance(StudentAttendance attendance);
    void deleteAttendance(long attendanceId);
}
