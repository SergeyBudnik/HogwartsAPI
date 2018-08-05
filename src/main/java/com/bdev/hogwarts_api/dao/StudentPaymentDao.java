package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.model.student_payment.StudentPaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentPaymentDao extends JpaRepository<StudentPaymentModel, Long> {
    List<StudentPaymentModel> getAllByStudentId(long studentId);
}
