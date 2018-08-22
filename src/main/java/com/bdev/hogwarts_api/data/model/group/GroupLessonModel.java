package com.bdev.hogwarts_api.data.model.group;

import com.bdev.hogwarts_api.data.dto.common.DayOfWeek;
import com.bdev.hogwarts_api.data.dto.common.LessonTime;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_GROUP_LESSON")
public class GroupLessonModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private GroupModel group;

    @Column(name = "TEACHER_ID")
    private long teacherId;

    @Column(name = "DAY")
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    @Column(name = "START_TIME")
    @Enumerated(EnumType.STRING)
    private LessonTime startTime;
    @Column(name = "FINISH_TIME")
    @Enumerated(EnumType.STRING)
    private LessonTime finishTime;
}
