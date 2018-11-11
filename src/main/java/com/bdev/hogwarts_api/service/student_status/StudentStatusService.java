package com.bdev.hogwarts_api.service.student_status;

import com.bdev.hogwarts_api.data.dto.student.StudentStatus;

import java.util.List;
import java.util.Optional;

public interface StudentStatusService {
    List<StudentStatus> getAllStudentsStatuses();
    List<StudentStatus> getAllStudentStatuses(long studentId);
    Optional<StudentStatus> getStudentStatus(long studentId);
    void changeStudentStatus(StudentStatus studentStatus);
}
