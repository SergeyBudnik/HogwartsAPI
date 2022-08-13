package com.bdev.hogwarts_api.rest.admin.staff_member

import com.bdev.hogwarts_api.data.dto.time.Month
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.staff_member.AdminStaffMemberActionsRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/staff-members/actions")
@Api(tags = ["Admin Staff Members Actions"], description = "PROTECTED")
class AdminStaffMemberActionsRest @Autowired constructor(
    private val adminStaffMemberActionsRestService: AdminStaffMemberActionsRestService
): CommonRest() {
    @GetMapping("/{staffMemberLogin}/weekIndex/{weekIndex}/month/{month}/year/{year}")
    fun getWeekActions(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @PathVariable("staffMemberLogin") staffMemberLogin: String,
        @PathVariable("weekIndex") weekIndex: Int,
        @PathVariable("month") month: Month,
        @PathVariable("year") year: Int
    ): ResponseEntity<*> {
        return adminStaffMemberActionsRestService.getWeekActions(
            userInfo = getUserInfo(authToken = authToken),
            staffMemberLogin = staffMemberLogin,
            weekIndex = weekIndex,
            month = month,
            year = year
        )
    }
}
