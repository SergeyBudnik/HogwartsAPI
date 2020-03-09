package com.bdev.hogwarts_api.data.converter.cabinet

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet
import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel
import com.bdev.hogwarts_api.utils.EncodingUtils

import com.bdev.hogwarts_api.utils.EncodingUtils.toBase64

object CabinetDtoConverter {
    fun convert(cabinet: Cabinet): CabinetModel {
        val cabinetModel = CabinetModel()

        cabinetModel.id = cabinet.id
        cabinetModel.name = toBase64(cabinet.name)

        return cabinetModel
    }
}
