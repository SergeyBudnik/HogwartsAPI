package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.data.dto.staff.StaffMember
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.staff.StaffMemberRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/staff/member/management")
@Api(tags = ["Staff", "Member", "Management"], description = "PROTECTED")
class AdminStaffMemberRest @Autowired constructor(
        private val staffMemberRestService: StaffMemberRestService
) : CommonRest() {
    @GetMapping("")
    fun getAllStaffMembers(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): ResponseEntity<List<StaffMember>> {
        return staffMemberRestService.getAllStaffMembers(
                userInfo = getUserInfo(authToken)
        )
    }

    @GetMapping("/{login}")
    fun getStaffMember(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable("login") login: String
    ): ResponseEntity<StaffMember> {
        return staffMemberRestService.getStaffMember(
                userInfo = getUserInfo(authToken),
                login = login
        )
    }

    @PostMapping("")
    fun createStaffMember(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody staffMember: StaffMember
    ): ResponseEntity<Nothing?> {
        return staffMemberRestService.createStaffMember(
                userInfo = getUserInfo(authToken),
                staffMember = staffMember
        )
    }

    @PutMapping("")
    fun editStaffMember(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @RequestBody staffMember: StaffMember
    ) {
        staffMemberRestService.updateStaffMember(
                userInfo = getUserInfo(authToken),
                staffMember = staffMember
        )
    }

    @DeleteMapping("/{login}")
    fun deleteStaffMember(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
            @PathVariable("login") login: String
    ) {
        staffMemberRestService.deleteStaffMember(
                userInfo = getUserInfo(authToken),
                login = login
        )
    }
}
