package com.bdev.hogwarts_api.service.student;

import com.bdev.hogwarts_api.data.dto.student.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    boolean exists(long studentId);
    List<Student> getAllStudents();
    Optional<Student> getStudentById(long studentId);
    long createStudent(Student student);
    void editStudent(Student student);
    void deleteStudent(long studentId);
}
