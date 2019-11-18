package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.staff.StaffMemberModel
import org.springframework.data.jpa.repository.JpaRepository

interface StaffMemberDao : JpaRepository<StaffMemberModel, String>
