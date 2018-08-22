package com.bdev.hogwarts_api.data.model.student_status;

import com.bdev.hogwarts_api.data.dto.student.StudentStatusType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_STUDENT_STATUS")
public class StudentStatusModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STUDENT_ID")
    private Long studentId;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StudentStatusType status;
    @Column(name = "CREATION_TIME")
    private Long creationTime;
    @Column(name = "ACTION_TIME")
    private Long actionTime;
}
