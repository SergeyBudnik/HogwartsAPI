package com.bdev.hogwarts_api.data.converter.staff

import com.bdev.hogwarts_api.data.dto.person.ExistingPersonInfo
import com.bdev.hogwarts_api.data.dto.staff.ExistingStaffMemberInfo
import com.bdev.hogwarts_api.data.dto.staff.StaffMember
import com.bdev.hogwarts_api.data.model.staff.StaffMemberModel

object StaffMemberModelConverter {
    fun convertExisting(staffMemberModel: StaffMemberModel, person: ExistingPersonInfo): ExistingStaffMemberInfo {
        return ExistingStaffMemberInfo(
                person = person.person,
                staffMember = StaffMember(login = staffMemberModel.login)
        )
    }
}
