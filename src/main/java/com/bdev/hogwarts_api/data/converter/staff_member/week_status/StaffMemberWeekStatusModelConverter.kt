package com.bdev.hogwarts_api.data.converter.staff_member.week_status

import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatus
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatusId
import com.bdev.hogwarts_api.data.model.staff_member.week_status.StaffMemberWeekStatusModel

object StaffMemberWeekStatusModelConverter {
    fun convert(model: StaffMemberWeekStatusModel): StaffMemberWeekStatus =
        StaffMemberWeekStatus(
            id = StaffMemberWeekStatusId(
                staffMemberLogin = model.id.staffMemberLogin,
                startTime = model.id.startTime,
                finishTime = model.id.finishTime
            ),
            type = model.type
        )
}
