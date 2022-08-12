package com.bdev.hogwarts_api.data.model.lesson_type

import javax.persistence.*

@Entity
@Table(name = "HG_LESSON_TYPE")
data class LessonTypeModel(
    @Id
    @Column(name = "ID")
    val id: String,

    @Column(name = "NAME")
    val name: String
) {
    constructor(): this(
        id = "",
        name = ""
    )
}
