package com.bdev.hogwarts_api.data.model.lesson_transfer

import javax.persistence.*

@Entity
@Table(name = "HG_LESSON_TRANSFER")
class LessonTransferModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "LESSON_ID")
    var lessonId: Long = 0
    @Column(name = "TEACHER_ID")
    var teacherId: Long = 0
    @Column(name = "FROM_TIME")
    var fromTime: Long = 0
    @Column(name = "TO_TIME")
    var toTime: Long = 0
}
