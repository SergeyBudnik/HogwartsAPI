package com.bdev.hogwarts_api.data.converter.season

import com.bdev.hogwarts_api.data.dto.season.Season
import com.bdev.hogwarts_api.data.dto.season.SeasonInfo
import com.bdev.hogwarts_api.data.model.season.SeasonModel

object SeasonModelConverter {
    fun convert(model: SeasonModel): Season {
        return Season(
            id = model.id ?: throw RuntimeException(),
            info = SeasonInfo(
                startTime = model.startTime,
                finishTime = model.finishTime
            ),
            active = model.active
        )
    }
}
