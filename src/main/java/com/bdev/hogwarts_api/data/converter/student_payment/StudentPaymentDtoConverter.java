package com.bdev.hogwarts_api.data.converter.student_payment;

import com.bdev.hogwarts_api.data.dto.student.StudentPayment;
import com.bdev.hogwarts_api.data.model.student_payment.StudentPaymentModel;

public class StudentPaymentDtoConverter {
    public static StudentPaymentModel convert(StudentPayment studentPayment) {
        StudentPaymentModel studentPaymentModel = new StudentPaymentModel();

        studentPaymentModel.setId(studentPayment.getId());
        studentPaymentModel.setStudentId(studentPayment.getStudentId());
        studentPaymentModel.setAmount(studentPayment.getAmount());
        studentPaymentModel.setTime(studentPayment.getTime());

        return studentPaymentModel;
    }
}
