package com.bdev.hogwarts_api.rest_service.event

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.events.Event

interface EventsRestService {
    fun getAll(userInfo: MunicipaliUserInfo): List<Event>
    fun getById(userInfo: MunicipaliUserInfo, id: Long): Event
    fun create(userInfo: MunicipaliUserInfo, speakingClub: Event): Long
    fun update(userInfo: MunicipaliUserInfo, speakingClub: Event)
    fun delete(userInfo: MunicipaliUserInfo, id: Long)
}
