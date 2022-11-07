package com.bdev.hogwarts_api.data.model.staff_member.week_status

import com.bdev.hogwarts_api.data.dto.time.Month
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Embeddable
data class StaffMemberWeekStatusModelId(
    @Column(name = "STAFF_MEMBER_LOGIN")
    val staffMemberLogin: String,
    @Column(name = "WEEK_INDEX")
    val weekIndex: Int,
    @Column(name = "MONTH")
    @Enumerated(EnumType.STRING)
    val month: Month,
    @Column(name = "YEAR")
    val year: Int
) : Serializable {
    @Suppress("unused")
    constructor(): this(
        staffMemberLogin = "",
        weekIndex = 0,
        month = Month.JAN,
        year = 0
    )
}
