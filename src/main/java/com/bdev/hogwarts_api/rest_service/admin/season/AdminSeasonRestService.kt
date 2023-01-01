package com.bdev.hogwarts_api.rest_service.admin.season

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.season.SeasonInfo
import org.springframework.http.ResponseEntity

interface AdminSeasonRestService {
    fun getAll(userInfo: MunicipaliUserInfo): ResponseEntity<*>
    fun getById(userInfo: MunicipaliUserInfo, id: Long): ResponseEntity<*>
    fun create(userInfo: MunicipaliUserInfo, seasonInfo: SeasonInfo): ResponseEntity<*>
    fun update(userInfo: MunicipaliUserInfo, id: Long, seasonInfo: SeasonInfo): ResponseEntity<*>
    fun activate(userInfo: MunicipaliUserInfo, id: Long): ResponseEntity<*>
    fun delete(userInfo: MunicipaliUserInfo, id: Long): ResponseEntity<*>
}
