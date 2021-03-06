package com.bdev.hogwarts_api.data.converter.staff

import com.bdev.hogwarts_api.data.dto.staff.StaffMember
import com.bdev.hogwarts_api.data.model.staff.StaffMemberModel

object StaffMemberDtoConverter {
    fun convert(staffMember: StaffMember, personId: Long): StaffMemberModel {
        val staffMemberModel = StaffMemberModel()

        staffMemberModel.login = staffMember.login
        staffMemberModel.personId = personId
        staffMemberModel.active = staffMember.active

        staffMemberModel.salaryIn30m = staffMember.salaryIn30m

        staffMemberModel.roleTeacher = staffMember.roles.teacher

        staffMemberModel.subscribeToFreeLessonRequest = staffMember.subscriptions.freeLessonRequest

        return staffMemberModel
    }
}
