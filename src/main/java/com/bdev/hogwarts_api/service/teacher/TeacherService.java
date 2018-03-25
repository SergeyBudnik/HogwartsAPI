package com.bdev.hogwarts_api.service.teacher;

import com.bdev.hogwarts_api.data.dto.teacher.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    boolean exists(long id);
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacher(long id);
    long createTeacher(Teacher teacher);
    void editTeacher(Teacher teacher);
    void deleteTeacher(long id);
}
