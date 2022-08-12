package com.bdev.hogwarts_api.service.price_rule

import com.bdev.hogwarts_api.dao.PriceRuleDao
import com.bdev.hogwarts_api.data.converter.price_rule.PriceRuleDtoConverter
import com.bdev.hogwarts_api.data.converter.price_rule.PriceRuleModelConverter
import com.bdev.hogwarts_api.data.dto.price_rule.PriceRule
import com.bdev.hogwarts_api.data.dto.price_rule.PriceRuleId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface PriceRuleStorageService {
    fun getAll(): List<PriceRule>
    fun set(priceRule: PriceRule)
    fun delete(priceRuleId: PriceRuleId): Boolean
}

@Service
class PriceRuleStorageServiceImpl @Autowired constructor(
    private val priceRuleDao: PriceRuleDao
): PriceRuleStorageService {
    override fun getAll() =
        priceRuleDao
            .findAll()
            .map(PriceRuleModelConverter::convert)

    override fun set(priceRule: PriceRule) {
        priceRuleDao.save(
            PriceRuleDtoConverter.convert(
                dto = priceRule
            )
        )
    }

    override fun delete(priceRuleId: PriceRuleId): Boolean {
        val priceRuleModelId = PriceRuleDtoConverter.convertId(
            dto = priceRuleId
        )

        val exists = priceRuleDao.exists(priceRuleModelId)

        if (exists) {
            priceRuleDao.delete(
                PriceRuleDtoConverter.convertId(
                    dto = priceRuleId
                )
            )
        }

        return exists
    }
}
