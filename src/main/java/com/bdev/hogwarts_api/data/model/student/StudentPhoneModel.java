package com.bdev.hogwarts_api.data.model.student;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_STUDENT_PHONE")
public class StudentPhoneModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private StudentModel student;

    @Column(name = "VALUE", length = 256)
    private String value;
}
