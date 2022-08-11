package com.bdev.hogwarts_api.data.model.staff_member.week_status

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class StaffMemberWeekStatusModelId(
    @Column(name = "STAFF_MEMBER_LOGIN")
    val staffMemberLogin: String,
    @Column(name = "START_TIME")
    val startTime: Long,
    @Column(name = "FINISH_TIME")
    val finishTime: Long
) : Serializable {
    @Suppress("unused")
    constructor(): this(
        staffMemberLogin = "",
        startTime = 0L,
        finishTime = 0L
    )
}
