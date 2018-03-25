package com.bdev.hogwarts_api.data.model.teacher;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_TEACHER_PHONE")
public class TeacherPhoneModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHER_ID", nullable = false)
    private TeacherModel teacher;

    @Column(name = "VALUE", length = 256)
    private String value;
}
