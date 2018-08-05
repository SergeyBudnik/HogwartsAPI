package com.bdev.hogwarts_api.service.teacher;

import com.bdev.hogwarts_api.data.dto.teacher.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    boolean exists(long id);
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacherById(long id);
    Optional<Teacher> getTeacherByLogin(String login);
    long createTeacher(Teacher teacher);
    void editTeacher(Teacher teacher);
    void deleteTeacher(long id);
}
