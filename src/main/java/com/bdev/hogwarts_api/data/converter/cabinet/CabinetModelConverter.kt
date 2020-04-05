package com.bdev.hogwarts_api.data.converter.cabinet

import com.bdev.hogwarts_api.data.dto.cabinet.CabinetInfo
import com.bdev.hogwarts_api.data.dto.cabinet.ExistingCabinet
import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel
import com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64

object CabinetModelConverter {
    fun convert(cabinetModel: CabinetModel): ExistingCabinet {
        return ExistingCabinet(
                id = cabinetModel.id ?: throw RuntimeException(),
                info = CabinetInfo(
                        name = fromBase64(cabinetModel.name)
                )
        )
    }
}
