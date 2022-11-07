package com.bdev.hogwarts_api.service.staff_member.week_status

import com.bdev.hogwarts_api.dao.StaffMemberWeekStatusDao
import com.bdev.hogwarts_api.data.converter.staff_member.week_status.StaffMemberWeekStatusDtoConverter
import com.bdev.hogwarts_api.data.converter.staff_member.week_status.StaffMemberWeekStatusModelConverter
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatus
import com.bdev.hogwarts_api.data.dto.staff_member.week_status.StaffMemberWeekStatusId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface StaffMemberWeekStatusStorageService {
    fun getAllForStaffMember(staffMemberLogin: String): List<StaffMemberWeekStatus>
    fun set(staffMemberWeekStatus: StaffMemberWeekStatus)
    fun delete(staffMemberWeekStatusId: StaffMemberWeekStatusId): Boolean
}

@Service
class StaffMemberWeekStatusStorageServiceImpl @Autowired constructor(
    private val staffMemberWeekStatusDao: StaffMemberWeekStatusDao
): StaffMemberWeekStatusStorageService {
    override fun getAllForStaffMember(staffMemberLogin: String): List<StaffMemberWeekStatus> {
        return staffMemberWeekStatusDao
            .getAllByIdStaffMemberLogin(staffMemberLogin = staffMemberLogin)
            .map { model -> StaffMemberWeekStatusModelConverter.convert(model = model) }
    }

    override fun set(staffMemberWeekStatus: StaffMemberWeekStatus) {
        staffMemberWeekStatusDao.save(
            StaffMemberWeekStatusDtoConverter.convert(
                dto = staffMemberWeekStatus
            )
        )
    }

    override fun delete(staffMemberWeekStatusId: StaffMemberWeekStatusId): Boolean {
        return StaffMemberWeekStatusDtoConverter.convertId(
            dto = staffMemberWeekStatusId
        ).let { idModel ->
            if (staffMemberWeekStatusDao.exists(idModel)) {
                staffMemberWeekStatusDao.delete(
                    StaffMemberWeekStatusDtoConverter.convertId(
                        dto = staffMemberWeekStatusId
                    )
                )

                true
            } else {
                false
            }
        }
    }
}
