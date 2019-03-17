package com.bdev.hogwarts_api.data.model.lesson_status

import com.bdev.hogwarts_api.data.dto.lesson.LessonStatusType
import javax.persistence.*

@Entity
@Table(name = "HG_LESSON_STATUS")
open class LessonStatusModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "LESSON_ID")
    var lessonId: Long = 0L
    @Column(name = "STATUS_TYPE")
    var type: LessonStatusType = LessonStatusType.FINISHED
    @Column(name = "ACTION_TIME")
    var actionTime: Long = 0L
    @Column(name = "CREATION_TIME")
    var creationTime: Long = 0L
}
