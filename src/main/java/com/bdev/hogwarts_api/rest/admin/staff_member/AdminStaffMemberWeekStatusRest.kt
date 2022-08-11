package com.bdev.hogwarts_api.rest.admin.staff_member

import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatus
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatusId
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.staff_member.AdminStaffMemberWeekStatusRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/staff-members/week-status")
@Api(tags = ["Staff Members Week Status"], description = "PROTECTED")
class AdminStaffMemberWeekStatusRest @Autowired constructor(
    private val staffMemberWeekStatusRestService: AdminStaffMemberWeekStatusRestService
) : CommonRest() {
    @GetMapping(value = ["/{staffMemberLogin}"])
    fun getAllStaffMembers(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @PathVariable("staffMemberLogin") staffMemberLogin: String
    ): ResponseEntity<*> = staffMemberWeekStatusRestService.getAllForStaffMember(
        userInfo = getUserInfo(authToken = authToken),
        staffMemberLogin = staffMemberLogin
    )

    @PostMapping(value = ["/"])
    fun set(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @RequestBody staffMemberWeekStatus: StaffMemberWeekStatus
    ): ResponseEntity<*> = staffMemberWeekStatusRestService.set(
        userInfo = getUserInfo(authToken = authToken),
        staffMemberWeekStatus = staffMemberWeekStatus
    )

    @DeleteMapping(value = ["/"])
    fun delete(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @RequestBody staffMemberWeekStatusId: StaffMemberWeekStatusId
    ): ResponseEntity<*> = staffMemberWeekStatusRestService.delete(
        userInfo = getUserInfo(authToken = authToken),
        staffMemberWeekStatusId = staffMemberWeekStatusId
    )
}
