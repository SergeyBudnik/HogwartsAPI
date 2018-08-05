package com.bdev.hogwarts_api.service.student_payment;

import com.bdev.hogwarts_api.data.dto.student.StudentPayment;

import java.util.List;

public interface StudentPaymentService {
    boolean exists(long paymentId);
    List<StudentPayment> getPayments();
    List<StudentPayment> getPayments(long studentId);
    long addPayment(StudentPayment payment);
    void deletePayment(long paymentId);
}
