package com.bdev.hogwarts_api.rest_service.student;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.Student;
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType;

import java.util.List;

public interface StudentRestService {
    List<Student> getAllStudents(MunicipaliUserInfo userInfo);
    Student getStudentById(MunicipaliUserInfo userInfo, long studentId);
    long createStudent(MunicipaliUserInfo userInfo, Student student);
    void editStudent(MunicipaliUserInfo userInfo, Student student);
    void deleteStudent(MunicipaliUserInfo userInfo, long studentId);
}
