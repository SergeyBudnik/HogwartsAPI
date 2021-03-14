package com.bdev.hogwarts_api.rest_service.common

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.staff.StaffMember
import org.springframework.http.ResponseEntity

interface CommonCRUDRestService <Id, Type> {
    fun getAll(userInfo: MunicipaliUserInfo): ResponseEntity<Any>
    fun getById(userInfo: MunicipaliUserInfo, id: Id): ResponseEntity<Any>
    fun create(userInfo: MunicipaliUserInfo, o: Type): ResponseEntity<Any>
    fun update(userInfo: MunicipaliUserInfo, o: Type): ResponseEntity<Any>
    fun delete(userInfo: MunicipaliUserInfo, id: Id): ResponseEntity<Any>
}
