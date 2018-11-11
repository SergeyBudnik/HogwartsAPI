package com.bdev.hogwarts_api.data.model.teacher;

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek;
import com.bdev.hogwarts_api.data.dto.common.LessonTime;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_TEACHER_AVAILABILITY")
public class TeacherAvailabilityModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHER_ID", nullable = false)
    private TeacherModel teacher;

    @Column(name = "DAY_OF_WEEK")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "AVAILABLE_TIME")
    @Enumerated(EnumType.STRING)
    private LessonTime time;
}
