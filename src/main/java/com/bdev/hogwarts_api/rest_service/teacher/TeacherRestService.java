package com.bdev.hogwarts_api.rest_service.teacher;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.teacher.Teacher;

import java.util.List;

public interface TeacherRestService {
    List<Teacher> getAllTeachers(MunicipaliUserInfo userInfo);
    Teacher getTeacherById(MunicipaliUserInfo userInfo, long id);
    Teacher getTeacherByLogin(MunicipaliUserInfo userInfo, String login);
    long createTeacher(MunicipaliUserInfo userInfo, Teacher teacher);
    void editTeacher(MunicipaliUserInfo userInfo, Teacher teacher);
    void deleteTeacher(MunicipaliUserInfo userInfo, long id);
}
