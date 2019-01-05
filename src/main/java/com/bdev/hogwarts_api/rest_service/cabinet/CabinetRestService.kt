package com.bdev.hogwarts_api.rest_service.cabinet

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet

interface CabinetRestService {
    fun getAllCabinets(userInfo: MunicipaliUserInfo): List<Cabinet>
    fun getCabinet(userInfo: MunicipaliUserInfo, cabinetId: Long): Cabinet
    fun saveCabinet(userInfo: MunicipaliUserInfo, cabinet: Cabinet): Long
    fun editCabinet(userInfo: MunicipaliUserInfo, cabinet: Cabinet)
    fun deleteCabinet(userInfo: MunicipaliUserInfo, cabinetId: Long)
}
