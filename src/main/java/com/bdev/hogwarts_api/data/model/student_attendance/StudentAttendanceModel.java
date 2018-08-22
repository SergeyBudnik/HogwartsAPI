package com.bdev.hogwarts_api.data.model.student_attendance;

import com.bdev.hogwarts_api.data.dto.student.StudentAttendanceType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_STUDENT_ATTENDANCE")
public class StudentAttendanceModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STUDENT_ID")
    private Long studentId;
    @Column(name = "ATTENDANCE_TYPE")
    @Enumerated(EnumType.STRING)
    private StudentAttendanceType type;
    @Column(name = "ATTENDANCE_TIME")
    private Long time;
}
