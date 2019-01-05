package com.bdev.hogwarts_api.data.model.student_payment

import lombok.Data

import javax.persistence.*

@Data
@Entity
@Table(name = "HG_STUDENT_PAYMENT")
open class StudentPaymentModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "STUDENT_ID")
    var studentId: Long? = null
    @Column(name = "PAYMENT_AMOUNT")
    var amount: Long? = null
    @Column(name = "PAYMENT_TIME")
    var time: Long? = null
}
