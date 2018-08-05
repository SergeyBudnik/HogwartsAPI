package com.bdev.hogwarts_api.data.converter.student_payment;

import com.bdev.hogwarts_api.data.dto.student.StudentPayment;
import com.bdev.hogwarts_api.data.model.student_payment.StudentPaymentModel;

public class StudentPaymentModelConverter {
    public static StudentPayment convert(StudentPaymentModel studentPaymentModel) {
        return StudentPayment.builder()
                .id(studentPaymentModel.getId())
                .studentId(studentPaymentModel.getStudentId())
                .amount(studentPaymentModel.getAmount())
                .time(studentPaymentModel.getTime())
                .build();
    }
}
