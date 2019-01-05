package com.bdev.hogwarts_api.service.cabinet

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet

interface CabinetService {
    fun getAllCabinets(): List<Cabinet>
    fun exists(id: Long): Boolean
    fun getCabinet(id: Long): Cabinet?
    fun saveCabinet(cabinet: Cabinet): Long
    fun editCabinet(cabinet: Cabinet)
    fun deleteCabinet(id: Long)
}
