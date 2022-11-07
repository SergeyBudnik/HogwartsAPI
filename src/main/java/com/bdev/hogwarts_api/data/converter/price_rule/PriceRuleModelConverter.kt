package com.bdev.hogwarts_api.data.converter.price_rule

import com.bdev.hogwarts_api.data.dto.price_rule.PriceRule
import com.bdev.hogwarts_api.data.dto.price_rule.PriceRuleId
import com.bdev.hogwarts_api.data.model.price_rule.PriceRuleModel

object PriceRuleModelConverter {
    fun convert(priceRuleModel: PriceRuleModel): PriceRule =
        PriceRule(
            id = PriceRuleId(
                lessonTypeId = priceRuleModel.id.lessonTypeId,
                startTime = priceRuleModel.id.startTime,
                finishTime = priceRuleModel.id.finishTime
            ),
            priceFor30m = priceRuleModel.priceFor30m
        )
}
