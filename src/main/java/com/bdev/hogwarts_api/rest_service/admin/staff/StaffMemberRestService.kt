package com.bdev.hogwarts_api.rest_service.admin.staff

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.staff.StaffMember
import com.bdev.hogwarts_api.service.staff.StaffMemberStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface StaffMemberRestService {
    fun getAllStaffMembers(userInfo: MunicipaliUserInfo): ResponseEntity<List<StaffMember>>
    fun getStaffMember(userInfo: MunicipaliUserInfo, login: String): ResponseEntity<StaffMember>
    fun createStaffMember(userInfo: MunicipaliUserInfo, staffMember: StaffMember): ResponseEntity<Nothing?>
    fun updateStaffMember(userInfo: MunicipaliUserInfo, staffMember: StaffMember): ResponseEntity<Nothing?>
    fun deleteStaffMember(userInfo: MunicipaliUserInfo, login: String): ResponseEntity<Nothing?>
}

@Service
open class StaffMemberRestServiceImpl @Autowired constructor(
        private val staffMemberStorageService: StaffMemberStorageService
) : StaffMemberRestService {
    @Transactional(readOnly = true)
    override fun getAllStaffMembers(userInfo: MunicipaliUserInfo): ResponseEntity<List<StaffMember>> {
        return ResponseEntity.ok(staffMemberStorageService.getAllStaffMembers())
    }

    @Transactional(readOnly = true)
    override fun getStaffMember(userInfo: MunicipaliUserInfo, login: String): ResponseEntity<StaffMember> {
        val staffMember = staffMemberStorageService.getStaffMember(login)

        return if (staffMember == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        } else {
            ResponseEntity.ok(staffMember)
        }
    }

    @Transactional
    override fun createStaffMember(userInfo: MunicipaliUserInfo, staffMember: StaffMember): ResponseEntity<Nothing?> {
        staffMemberStorageService.createStaffMember(staffMember)

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Transactional
    override fun updateStaffMember(userInfo: MunicipaliUserInfo, staffMember: StaffMember): ResponseEntity<Nothing?> {
        staffMemberStorageService.updateStaffMember(staffMember)

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Transactional
    override fun deleteStaffMember(userInfo: MunicipaliUserInfo, login: String): ResponseEntity<Nothing?> {
        staffMemberStorageService.deleteStaffMember(login)

        return ResponseEntity.status(HttpStatus.OK).build()
    }
}
