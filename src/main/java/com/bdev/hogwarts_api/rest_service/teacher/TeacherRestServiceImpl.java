package com.bdev.hogwarts_api.rest_service.teacher;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.teacher.Teacher;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherRestServiceImpl implements TeacherRestService {
    @Autowired
    private TeacherService teacherService;

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> getAllTeachers(
            MunicipaliUserInfo userInfo
    ) {
        return teacherService.getAllTeachers();
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher getTeacherById(
            MunicipaliUserInfo userInfo,
            long id
    ) {
        return teacherService.getTeacherById(id)
                .orElseThrow(() -> new HttpEntityNotFoundException("Teacher with id '%d' does not exist", id));
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher getTeacherByLogin(
            MunicipaliUserInfo userInfo,
            String login
    ) {
        return teacherService.getTeacherByLogin(login)
                .orElseThrow(() -> new HttpEntityNotFoundException("Teacher with login '%s' does not exist", login));
    }

    @Transactional
    @Override
    public long createTeacher(
            MunicipaliUserInfo userInfo,
            Teacher teacher
    ) {
        if (teacher.getId() != null) {
            throw new HttpEntityIllegalStateException("Teacher id should be null during creation");
        }

        return teacherService.createTeacher(teacher);
    }

    @Transactional
    @Override
    public void editTeacher(
            MunicipaliUserInfo userInfo,
            Teacher teacher
    ) {
        if (teacher.getId() == null) {
            throw new HttpEntityIllegalStateException("Teacher id should be non-null during edit");
        }

        if (!teacherService.exists(teacher.getId())) {
            throw new HttpEntityNotFoundException("Teacher with id '%d' does not exist", teacher.getId());
        }

        teacherService.editTeacher(teacher);
    }

    @Transactional
    @Override
    public void deleteTeacher(
            MunicipaliUserInfo userInfo,
            long id
    ) {
        if (!teacherService.exists(id)) {
            throw new HttpEntityNotFoundException("Teacher with id '%d' does not exist", id);
        }

        teacherService.deleteTeacher(id);
    }
}
