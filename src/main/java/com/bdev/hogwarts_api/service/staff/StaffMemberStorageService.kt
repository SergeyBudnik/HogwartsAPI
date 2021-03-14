package com.bdev.hogwarts_api.service.staff

import com.bdev.hogwarts_api.dao.StaffMemberDao
import com.bdev.hogwarts_api.data.converter.staff.StaffMemberDtoConverter
import com.bdev.hogwarts_api.data.converter.staff.StaffMemberModelConverter
import com.bdev.hogwarts_api.data.dto.person.ExistingPersonInfo
import com.bdev.hogwarts_api.data.dto.person.NewPersonInfo
import com.bdev.hogwarts_api.data.dto.staff.StaffMember
import com.bdev.hogwarts_api.service.person.PersonStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface StaffMemberStorageService {
    fun getAllStaffMembers(): List<StaffMember>
    fun getStaffMember(login: String): StaffMember?
    fun createStaffMember(staffMember: StaffMember)
    fun updateStaffMember(staffMember: StaffMember)
    fun deleteStaffMember(login: String)
}

@Service
open class StaffMemberStorageServiceImpl @Autowired constructor(
        private val personStorageService: PersonStorageService,
        private val staffMemberDao: StaffMemberDao
) : StaffMemberStorageService {
    override fun getAllStaffMembers(): List<StaffMember> {
        return staffMemberDao.findAll().mapNotNull {
            personStorageService.withExistingPerson(it.personId) { person ->
                StaffMemberModelConverter.convertExisting(
                        staffMemberModel = it,
                        person = person
                )
            }
        }
    }

    override fun getStaffMember(login: String): StaffMember? {
        val staffMemberModel = staffMemberDao.findOne(login)

        return if (staffMemberModel == null) {
            null
        } else {
            return personStorageService.withExistingPerson(staffMemberModel.personId) { person ->
                StaffMemberModelConverter.convertExisting(
                        staffMemberModel = staffMemberModel,
                        person = person
                )
            }
        }
    }

    override fun createStaffMember(staffMember: StaffMember) {
        val personId = personStorageService.createPerson(
                NewPersonInfo(staffMember.person)
        )

        staffMemberDao.save(
                StaffMemberDtoConverter.convert(
                        staffMember = staffMember,
                        personId = personId
                )
        )
    }

    override fun updateStaffMember(staffMember: StaffMember) {
        val staffMemberModel = staffMemberDao.findOne(staffMember.login)

        if (staffMemberModel != null) {
            personStorageService.updatePerson(
                    ExistingPersonInfo(
                            id = staffMemberModel.personId,
                            person = staffMember.person
                    )
            )

            staffMemberDao.save(
                    StaffMemberDtoConverter.convert(
                            staffMember = staffMember,
                            personId = staffMemberModel.personId
                    )
            )
        }
    }

    override fun deleteStaffMember(login: String) {
        val staffMemberModel = staffMemberDao.findOne(login)

        if (staffMemberModel != null) {
            staffMemberDao.delete(login)
            personStorageService.deletePerson(staffMemberModel.personId)
        }
    }
}
