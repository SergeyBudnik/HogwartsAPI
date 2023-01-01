package com.bdev.hogwarts_api.service.season.storage

import com.bdev.hogwarts_api.data.dto.season.Season
import com.bdev.hogwarts_api.data.dto.season.SeasonInfo

interface SeasonStorageService {
    fun exists(id: Long): Boolean
    fun getAll(): List<Season>
    fun getById(id: Long): Season?
    fun create(seasonInfo: SeasonInfo): Long
    fun update(id: Long, seasonInfo: SeasonInfo)
    fun activate(id: Long, active: Boolean)
    fun delete(id: Long)
}
