package com.bdev.hogwarts_api.rest_service.student_status;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.StudentStatus;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.student.StudentService;
import com.bdev.hogwarts_api.service.student_status.StudentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentStatusRestServiceImpl implements StudentStatusRestService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentStatusService studentStatusService;

    @Transactional(readOnly = true)
    @Override
    public List<StudentStatus> getStudentStatuses(MunicipaliUserInfo userInfo, long studentId) {
        if (!studentService.exists(studentId)) {
            throw new HttpEntityNotFoundException("Student with id '%d' does not exist", studentId);
        }

        return studentStatusService.getAllStudentStatuses(studentId);
    }
}