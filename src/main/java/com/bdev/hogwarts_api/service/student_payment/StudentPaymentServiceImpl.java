package com.bdev.hogwarts_api.service.student_payment;

import com.bdev.hogwarts_api.dao.StudentPaymentDao;
import com.bdev.hogwarts_api.data.converter.student_payment.StudentPaymentDtoConverter;
import com.bdev.hogwarts_api.data.converter.student_payment.StudentPaymentModelConverter;
import com.bdev.hogwarts_api.data.dto.student.StudentPayment;
import com.bdev.hogwarts_api.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class StudentPaymentServiceImpl implements StudentPaymentService {
    @Autowired
    private StudentPaymentDao studentPaymentDao;

    @Autowired
    private StudentService studentService;

    @Override
    public boolean exists(long paymentId) {
        return studentPaymentDao.exists(paymentId);
    }

    @Override
    public List<StudentPayment> getPayments() {
        return studentPaymentDao
                .findAll()
                .stream()
                .map(StudentPaymentModelConverter::convert)
                .collect(toList());
    }

    @Override
    public List<StudentPayment> getPayments(long studentId) {
        if (!studentService.getStudentById(studentId).isPresent()) {
            throw new RuntimeException(format("Student with id '%d' does not exist", studentId));
        }

        return studentPaymentDao
                .getAllByStudentId(studentId)
                .stream()
                .map(StudentPaymentModelConverter::convert)
                .collect(toList());
    }

    @Override
    public long addPayment(StudentPayment payment) {
        if (!studentService.getStudentById(payment.getStudentId()).isPresent()) {
            throw new RuntimeException(format("Student with id '%d' does not exist", payment.getStudentId()));
        }

        return studentPaymentDao.save(StudentPaymentDtoConverter.convert(payment)).getId();
    }

    @Override
    public void deletePayment(long paymentId) {
        if (!studentPaymentDao.exists(paymentId)) {
            throw new RuntimeException(format("Student payment with id '%d' does not exist", paymentId));
        }

        studentPaymentDao.delete(paymentId);
    }
}
