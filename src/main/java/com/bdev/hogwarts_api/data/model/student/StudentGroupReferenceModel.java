package com.bdev.hogwarts_api.data.model.student;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_STUDENT_GROUP_REF")
public class StudentGroupReferenceModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private StudentModel student;

    @Column(name = "GROUP_ID", length = 256)
    private Long groupId;
}
