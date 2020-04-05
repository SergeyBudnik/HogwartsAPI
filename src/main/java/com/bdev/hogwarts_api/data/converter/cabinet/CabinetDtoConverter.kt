package com.bdev.hogwarts_api.data.converter.cabinet

import com.bdev.hogwarts_api.data.dto.cabinet.ExistingCabinet
import com.bdev.hogwarts_api.data.dto.cabinet.NewCabinet
import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel

import com.bdev.hogwarts_api.utils.EncodingUtils.toBase64

object CabinetDtoConverter {
    fun convertNew(cabinet: NewCabinet): CabinetModel {
        return CabinetModel(
                id = null,
                name = toBase64(cabinet.info.name)
        )
    }

    fun convertExisting(cabinet: ExistingCabinet): CabinetModel {
        return CabinetModel(
                id = cabinet.id,
                name = toBase64(cabinet.info.name)
        )
    }
}
