package com.bdev.hogwarts_api.rest_service.student_payment;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.StudentPayment;

import java.util.List;

public interface StudentPaymentRestService {
    List<StudentPayment> getPayments(MunicipaliUserInfo userInfo);
    List<StudentPayment> getPayments(MunicipaliUserInfo userInfo, long studentId);
    long addPayment(MunicipaliUserInfo userInfo, StudentPayment payment);
    void deletePayment(MunicipaliUserInfo userInfo, long paymentId);
}
