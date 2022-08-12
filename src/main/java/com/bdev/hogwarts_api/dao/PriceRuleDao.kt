package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.price_rule.PriceRuleModel
import com.bdev.hogwarts_api.data.model.price_rule.PriceRuleModelId
import org.springframework.data.jpa.repository.JpaRepository

interface PriceRuleDao : JpaRepository<PriceRuleModel, PriceRuleModelId>
