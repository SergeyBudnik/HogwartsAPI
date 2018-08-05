package com.bdev.hogwarts_api.rest_service.student_payment;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.StudentPayment;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.student.StudentService;
import com.bdev.hogwarts_api.service.student_payment.StudentPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentPaymentRestServiceImpl implements StudentPaymentRestService {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentPaymentService studentPaymentService;

    @Override
    public List<StudentPayment> getPayments(MunicipaliUserInfo userInfo) {
        return studentPaymentService.getPayments();
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentPayment> getPayments(MunicipaliUserInfo userInfo, long studentId) {
        if (!studentService.exists(studentId)) {
            throw new HttpEntityNotFoundException("Student with id '%d' does not exist", studentId);
        }

        return studentPaymentService.getPayments(studentId);
    }

    @Transactional
    @Override
    public long addPayment(MunicipaliUserInfo userInfo, StudentPayment payment) {
        if (!studentService.exists(payment.getStudentId())) {
            throw new HttpEntityNotFoundException("Student with id '%d' does not exist", payment.getStudentId());
        }

        return studentPaymentService.addPayment(payment);
    }

    @Transactional
    @Override
    public void deletePayment(MunicipaliUserInfo userInfo, long paymentId) {
        if (!studentPaymentService.exists(paymentId)) {
            throw new HttpEntityNotFoundException("Student payment with id '%d' does not exist", paymentId);
        }

        studentPaymentService.deletePayment(paymentId);
    }
}
