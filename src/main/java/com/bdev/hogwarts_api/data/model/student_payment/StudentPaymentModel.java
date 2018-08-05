package com.bdev.hogwarts_api.data.model.student_payment;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_STUDENT_PAYMENT")
public class StudentPaymentModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STUDENT_ID")
    private Long studentId;
    @Column(name = "PAYMENT_AMOUNT")
    private Long amount;
    @Column(name = "PAYMENT_TIME")
    private Long time;
}
