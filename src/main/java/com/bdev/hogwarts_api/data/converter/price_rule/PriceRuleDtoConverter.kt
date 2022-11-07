package com.bdev.hogwarts_api.data.converter.price_rule

import com.bdev.hogwarts_api.data.dto.price_rule.PriceRule
import com.bdev.hogwarts_api.data.dto.price_rule.PriceRuleId
import com.bdev.hogwarts_api.data.model.price_rule.PriceRuleModel
import com.bdev.hogwarts_api.data.model.price_rule.PriceRuleModelId

object PriceRuleDtoConverter {
    fun convert(dto: PriceRule): PriceRuleModel =
        PriceRuleModel(
            id = convertId(dto = dto.id),
            priceFor30m = dto.priceFor30m
        )

    fun convertId(dto: PriceRuleId): PriceRuleModelId =
        PriceRuleModelId(
            lessonTypeId = dto.lessonTypeId,
            startTime = dto.startTime,
            finishTime = dto.finishTime
        )
}
