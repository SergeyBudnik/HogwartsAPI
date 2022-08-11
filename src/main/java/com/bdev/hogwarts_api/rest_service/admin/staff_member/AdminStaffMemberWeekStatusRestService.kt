package com.bdev.hogwarts_api.rest_service.admin.staff_member

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatus
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatusId
import com.bdev.hogwarts_api.service.staff_member.week_status.StaffMemberWeekStatusStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface AdminStaffMemberWeekStatusRestService {
    fun getAllForStaffMember(
        userInfo: MunicipaliUserInfo,
        staffMemberLogin: String
    ): ResponseEntity<List<StaffMemberWeekStatus>>

    fun set(
        userInfo: MunicipaliUserInfo,
        staffMemberWeekStatus: StaffMemberWeekStatus
    ): ResponseEntity<Nothing?>

    fun delete(
        userInfo: MunicipaliUserInfo,
        staffMemberWeekStatusId: StaffMemberWeekStatusId
    ): ResponseEntity<Nothing?>
}

@Service
open class AdminStaffMemberWeekStatusRestServiceImpl @Autowired constructor(
    private val staffMemberWeekStatusStorageService: StaffMemberWeekStatusStorageService
): AdminStaffMemberWeekStatusRestService {
    @Transactional(readOnly = true)
    override fun getAllForStaffMember(
        userInfo: MunicipaliUserInfo,
        staffMemberLogin: String
    ): ResponseEntity<List<StaffMemberWeekStatus>> =
        ResponseEntity.ok(
            staffMemberWeekStatusStorageService.getAllForStaffMember(
                staffMemberLogin = staffMemberLogin
            )
        )

    @Transactional(readOnly = false)
    override fun set(
        userInfo: MunicipaliUserInfo,
        staffMemberWeekStatus: StaffMemberWeekStatus
    ): ResponseEntity<Nothing?> {
        staffMemberWeekStatusStorageService.set(staffMemberWeekStatus = staffMemberWeekStatus)

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    override fun delete(
        userInfo: MunicipaliUserInfo,
        staffMemberWeekStatusId: StaffMemberWeekStatusId
    ): ResponseEntity<Nothing?> {
        staffMemberWeekStatusStorageService.delete(
            staffMemberWeekStatusId = staffMemberWeekStatusId
        )

        return ResponseEntity.status(HttpStatus.OK).build()
    }
}
