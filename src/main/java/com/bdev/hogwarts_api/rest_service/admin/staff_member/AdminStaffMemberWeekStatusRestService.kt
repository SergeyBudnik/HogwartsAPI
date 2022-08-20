package com.bdev.hogwarts_api.rest_service.admin.staff_member

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatus
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatusId
import com.bdev.hogwarts_api.service.staff.StaffMemberStorageService
import com.bdev.hogwarts_api.service.staff_member.week_status.StaffMemberWeekStatusProviderService
import com.bdev.hogwarts_api.service.staff_member.week_status.StaffMemberWeekStatusStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface AdminStaffMemberWeekStatusRestService {
    fun getAllForStaffMember(
        userInfo: MunicipaliUserInfo,
        staffMemberLogin: String
    ): ResponseEntity<*>

    fun set(
        userInfo: MunicipaliUserInfo,
        staffMemberWeekStatus: StaffMemberWeekStatus
    ): ResponseEntity<*>

    fun delete(
        userInfo: MunicipaliUserInfo,
        staffMemberWeekStatusId: StaffMemberWeekStatusId
    ): ResponseEntity<*>
}

@Service
open class AdminStaffMemberWeekStatusRestServiceImpl @Autowired constructor(
    private val staffMemberStorageService: StaffMemberStorageService,
    private val staffMemberWeekStatusStorageService: StaffMemberWeekStatusStorageService,
    private val staffMemberWeekStatusProviderService: StaffMemberWeekStatusProviderService
): AdminStaffMemberWeekStatusRestService {
    @Transactional(readOnly = true)
    override fun getAllForStaffMember(
        userInfo: MunicipaliUserInfo,
        staffMemberLogin: String
    ): ResponseEntity<*> =
        ResponseEntity.ok(
            staffMemberWeekStatusProviderService.getStaffMemberWeekStatuses(
                staffMemberLogin = staffMemberLogin,
                calculationTime = Date().time
            )
        )

    @Transactional(readOnly = false)
    override fun set(
        userInfo: MunicipaliUserInfo,
        staffMemberWeekStatus: StaffMemberWeekStatus
    ): ResponseEntity<*> {
        val staffMember = staffMemberStorageService.getStaffMember(login = staffMemberWeekStatus.id.staffMemberLogin)

        return if (staffMember != null) {
            staffMemberWeekStatusStorageService.set(
                staffMemberWeekStatus = staffMemberWeekStatus
            )

            ResponseEntity
                .status(HttpStatus.OK)
                .body("Success")
        } else {
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Staff member by login '${staffMemberWeekStatus.id.staffMemberLogin}' does not exist")
        }
    }

    override fun delete(
        userInfo: MunicipaliUserInfo,
        staffMemberWeekStatusId: StaffMemberWeekStatusId
    ): ResponseEntity<*> {
        val deleted = staffMemberWeekStatusStorageService.delete(
            staffMemberWeekStatusId = staffMemberWeekStatusId
        )

        return if (deleted) {
            ResponseEntity
                .status(HttpStatus.OK)
                .body("Success")
        } else {
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Staff member week status does not exist for the given id")
        }
    }
}
