package com.bdev.hogwarts_api.data.model.events;

import com.bdev.hogwarts_api.data.dto.common.LessonTime;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_EVENT")
public class EventModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CABINET_ID")
    private Long cabinetId;
    @Column(name = "TEACHER_ID")
    private Long teacherId;
    @Column(name = "DATE")
    private Long date;
    @Column(name = "START_TIME")
    @Enumerated(EnumType.STRING)
    private LessonTime startTime;
    @Column(name = "FINISH_TIME")
    @Enumerated(EnumType.STRING)
    private LessonTime finishTime;
}
