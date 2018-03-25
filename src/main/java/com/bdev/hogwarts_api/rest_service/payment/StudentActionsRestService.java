package com.bdev.hogwarts_api.rest_service.payment;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;
import com.bdev.hogwarts_api.data.dto.student.StudentPayment;

public interface StudentActionsRestService {
    void addAttendance(MunicipaliUserInfo userInfo, StudentAttendance attendance);
    void addPayment(MunicipaliUserInfo userInfo, StudentPayment payment);
}
