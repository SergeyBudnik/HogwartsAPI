package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.staff_member.week_status.StaffMemberWeekStatusModel
import com.bdev.hogwarts_api.data.model.staff_member.week_status.StaffMemberWeekStatusModelId
import org.springframework.data.jpa.repository.JpaRepository

interface StaffMemberWeekStatusDao : JpaRepository<StaffMemberWeekStatusModel, StaffMemberWeekStatusModelId> {
    fun getAllByIdStaffMemberLogin(staffMemberLogin: String): List<StaffMemberWeekStatusModel>
}
