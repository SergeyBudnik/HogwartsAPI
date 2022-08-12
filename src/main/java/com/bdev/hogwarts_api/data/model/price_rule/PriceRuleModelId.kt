package com.bdev.hogwarts_api.data.model.price_rule

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class PriceRuleModelId(
    @Column(name = "LESSON_TYPE_ID")
    val lessonTypeId: String,
    @Column(name = "START_TIME")
    val startTime: Long,
    @Column(name = "FINISH_TIME")
    val finishTime: Long
): Serializable {
    constructor(): this(
        lessonTypeId = "",
        startTime = 0L,
        finishTime = 0L
    )
}
