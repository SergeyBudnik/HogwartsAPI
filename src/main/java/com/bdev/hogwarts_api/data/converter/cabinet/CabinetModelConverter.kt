package com.bdev.hogwarts_api.data.converter.cabinet

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet
import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel
import com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64

object CabinetModelConverter {
    fun convert(cabinetModel: CabinetModel): Cabinet {
        return Cabinet(
                cabinetModel.id,
                fromBase64(cabinetModel.name ?: throw RuntimeException()),
                cabinetModel.cabinetType ?: throw RuntimeException()
        )
    }
}
