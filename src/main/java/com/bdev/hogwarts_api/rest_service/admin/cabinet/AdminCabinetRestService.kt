package com.bdev.hogwarts_api.rest_service.admin.cabinet

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.cabinet.ExistingCabinet
import com.bdev.hogwarts_api.data.dto.cabinet.NewCabinet
import org.springframework.http.ResponseEntity

interface AdminCabinetRestService {
    fun getAllCabinets(userInfo: MunicipaliUserInfo): ResponseEntity<Any>
    fun getCabinet(userInfo: MunicipaliUserInfo, cabinetId: Long): ResponseEntity<Any>
    fun saveCabinet(userInfo: MunicipaliUserInfo, cabinet: NewCabinet): ResponseEntity<Any>
    fun editCabinet(userInfo: MunicipaliUserInfo, cabinet: ExistingCabinet): ResponseEntity<Any>
    fun deleteCabinet(userInfo: MunicipaliUserInfo, cabinetId: Long): ResponseEntity<Any>
}
