package com.bdev.hogwarts_api.data.model.season

import javax.persistence.*

@Entity
@Table(name = "HG_SEASON")
data class SeasonModel(
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,

    @Column(name = "START_TIME")
    val startTime: Long,
    @Column(name = "FINISH_TIME")
    val finishTime: Long,
    @Column(name = "ACTIVE")
    val active: Boolean
) {
    constructor(): this(
        id = null,
        startTime = 0L,
        finishTime = 0L,
        active = false
    )
}
