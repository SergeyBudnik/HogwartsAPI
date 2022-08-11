package com.bdev.hogwarts_api.data.model.staff_member.week_status

import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatusType
import javax.persistence.*

@Entity
@Table(name = "HG_STAFF_MEMBER_WEEK_STATUS")
data class StaffMemberWeekStatusModel(
    @EmbeddedId
    val id: StaffMemberWeekStatusModelId,

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    val type: StaffMemberWeekStatusType
) {
    @Suppress("unused")
    constructor(): this(
        id = StaffMemberWeekStatusModelId(),
        type = StaffMemberWeekStatusType.OPENED
    )
}
