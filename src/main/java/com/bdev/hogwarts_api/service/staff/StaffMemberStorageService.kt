package com.bdev.hogwarts_api.service.staff

import com.bdev.hogwarts_api.dao.StaffMemberDao
import com.bdev.hogwarts_api.data.converter.staff.StaffMemberDtoConverter
import com.bdev.hogwarts_api.data.converter.staff.StaffMemberModelConverter
import com.bdev.hogwarts_api.data.dto.person.ExistingPersonInfo
import com.bdev.hogwarts_api.data.dto.person.NewPersonInfo
import com.bdev.hogwarts_api.data.dto.staff.ExistingStaffMemberInfo
import com.bdev.hogwarts_api.data.dto.staff.NewStaffMemberInfo
import com.bdev.hogwarts_api.service.person.PersonStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface StaffMemberStorageService {
    fun getAllStaffMembers(): List<ExistingStaffMemberInfo>
    fun getStaffMember(login: String): ExistingStaffMemberInfo?
    fun createStaffMember(staffMember: NewStaffMemberInfo)
    fun updateStaffMember(staffMember: ExistingStaffMemberInfo)
    fun deleteStaffMember(login: String)
}

@Service
open class StaffMemberStorageServiceImpl @Autowired constructor(
        private val personStorageService: PersonStorageService,
        private val staffMemberDao: StaffMemberDao
) : StaffMemberStorageService {
    override fun getAllStaffMembers(): List<ExistingStaffMemberInfo> {
        val personsMap = personStorageService.getAllPersons().map { it.id to it }.toMap()

        return staffMemberDao.findAll().mapNotNull {
            val person = personsMap[it.personId]

            if (person == null) {
                null
            } else {
                StaffMemberModelConverter.convertExisting(
                        staffMemberModel = it,
                        person = person
                )
            }
        }
    }

    override fun getStaffMember(login: String): ExistingStaffMemberInfo? {
        val staffMemberModel = staffMemberDao.findOne(login)

        return if (staffMemberModel == null) {
            null
        } else {
            val person = personStorageService.getPerson(staffMemberModel.personId)

            return if (person == null) {
                null
            } else {
                StaffMemberModelConverter.convertExisting(
                        staffMemberModel = staffMemberModel,
                        person = person
                )
            }
        }
    }

    override fun createStaffMember(staffMember: NewStaffMemberInfo) {
        val personId = personStorageService.createPerson(
                NewPersonInfo(staffMember.person)
        )

        staffMemberDao.save(
                StaffMemberDtoConverter.convert(
                        staffMember = staffMember.staffMember,
                        personId = personId
                )
        )
    }

    override fun updateStaffMember(staffMember: ExistingStaffMemberInfo) {
        val staffMemberModel = staffMemberDao.findOne(staffMember.staffMember.login)

        if (staffMemberModel != null) {
            personStorageService.updatePerson(
                    ExistingPersonInfo(
                            id = staffMemberModel.personId,
                            person = staffMember.person
                    )
            )

            staffMemberDao.save(
                    StaffMemberDtoConverter.convert(
                            staffMember = staffMember.staffMember,
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
