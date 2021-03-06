package com.bdev.hogwarts_api.data.converter.staff

import com.bdev.hogwarts_api.data.dto.person.ExistingPersonInfo
import com.bdev.hogwarts_api.data.dto.staff.StaffMember
import com.bdev.hogwarts_api.data.dto.staff.StaffMemberRoles
import com.bdev.hogwarts_api.data.dto.staff.StaffMemberSubscriptions
import com.bdev.hogwarts_api.data.model.staff.StaffMemberModel

object StaffMemberModelConverter {
    fun convertExisting(staffMemberModel: StaffMemberModel, person: ExistingPersonInfo): StaffMember {
        return StaffMember(
                person = person.person,
                login = staffMemberModel.login,
                active = staffMemberModel.active,
                salaryIn30m = staffMemberModel.salaryIn30m,
                roles = StaffMemberRoles(
                        teacher = staffMemberModel.roleTeacher
                ),
                subscriptions = StaffMemberSubscriptions(
                        freeLessonRequest = staffMemberModel.subscribeToFreeLessonRequest
                )
        )
    }
}
