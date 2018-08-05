package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.model.student_attendance.StudentAttendanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentAttendanceDao extends JpaRepository<StudentAttendanceModel, Long> {
    List<StudentAttendanceModel> getAllByStudentId(long studentId);
}
