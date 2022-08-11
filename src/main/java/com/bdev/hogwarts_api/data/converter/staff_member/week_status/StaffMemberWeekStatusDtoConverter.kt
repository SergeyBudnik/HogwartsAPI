package com.bdev.hogwarts_api.data.converter.staff_member.week_status

import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatus
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatusId
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatusType
import com.bdev.hogwarts_api.data.model.staff_member.week_status.StaffMemberWeekStatusModel
import com.bdev.hogwarts_api.data.model.staff_member.week_status.StaffMemberWeekStatusModelId

object StaffMemberWeekStatusDtoConverter {
    fun convert(dto: StaffMemberWeekStatus): StaffMemberWeekStatusModel =
        StaffMemberWeekStatusModel(
            id = convertId(dto = dto.id),
            type = StaffMemberWeekStatusType.OPENED
        )

    fun convertId(dto: StaffMemberWeekStatusId): StaffMemberWeekStatusModelId =
        StaffMemberWeekStatusModelId(
            staffMemberLogin = dto.staffMemberLogin,
            startTime = dto.startTime,
            finishTime = dto.finishTime
        )
}
