package com.bdev.hogwarts_api.service.cabinet

import com.bdev.hogwarts_api.data.dto.cabinet.ExistingCabinet
import com.bdev.hogwarts_api.data.dto.cabinet.NewCabinet

interface CabinetService {
    fun getAllCabinets(): List<ExistingCabinet>
    fun exists(id: Long): Boolean
    fun getCabinet(id: Long): ExistingCabinet?
    fun saveCabinet(cabinet: NewCabinet): Long
    fun editCabinet(cabinet: ExistingCabinet)
    fun deleteCabinet(id: Long)
}
