package com.bdev.hogwarts_api.rest_service.payment;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;
import com.bdev.hogwarts_api.data.dto.student.StudentPayment;
import com.bdev.hogwarts_api.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentActionsRestServiceImpl implements StudentActionsRestService {
    @Autowired
    private StudentService studentService;

    @Override
    public void addAttendance(MunicipaliUserInfo userInfo, StudentAttendance attendance) {

    }

    @Override
    public void addPayment(MunicipaliUserInfo userInfo, StudentPayment payment) {

    }
}
