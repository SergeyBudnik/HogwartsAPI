package com.bdev.hogwarts_api.rest_service.admin.staff_member

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.time.Month
import com.bdev.hogwarts_api.service.staff_member.action.StaffMemberActionsProviderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface AdminStaffMemberActionsRestService {
    fun getWeekActions(
        userInfo: MunicipaliUserInfo,
        staffMemberLogin: String,
        weekIndex: Int,
        month: Month,
        year: Int
    ): ResponseEntity<*>
}

@Service
open class AdminStaffMemberActionsRestServiceImpl @Autowired constructor(
    private val staffMemberActionsProviderService: StaffMemberActionsProviderService
): AdminStaffMemberActionsRestService {
    @Transactional(readOnly = true)
    override fun getWeekActions(
        userInfo: MunicipaliUserInfo,
        staffMemberLogin: String,
        weekIndex: Int,
        month: Month,
        year: Int
    ): ResponseEntity<*> {
        return ResponseEntity.ok(
            staffMemberActionsProviderService.getWeekActions(
                staffMemberLogin = staffMemberLogin,
                weekIndex = weekIndex,
                month = month,
                year = year,
                calculationTime = Date().time
            )
        )
    }
}
