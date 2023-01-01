package com.bdev.hogwarts_api.data.converter.season

import com.bdev.hogwarts_api.data.dto.season.Season
import com.bdev.hogwarts_api.data.dto.season.SeasonInfo
import com.bdev.hogwarts_api.data.model.season.SeasonModel

object SeasonDtoConverter {
    fun convert(dto: SeasonInfo): SeasonModel {
        return convert(
            id = null,
            seasonInfo = dto,
            active = false
        )
    }

    fun convert(dto: Season): SeasonModel {
        return convert(
            id = dto.id,
            seasonInfo = dto.info,
            active = dto.active
        )
    }

    private fun convert(id: Long?, seasonInfo: SeasonInfo, active: Boolean): SeasonModel {
        return SeasonModel(
            id = id,
            startTime = seasonInfo.startTime,
            finishTime = seasonInfo.finishTime,
            active = active
        )
    }
}
