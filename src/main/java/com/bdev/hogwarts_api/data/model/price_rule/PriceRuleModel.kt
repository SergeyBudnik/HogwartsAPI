package com.bdev.hogwarts_api.data.model.price_rule

import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "HG_PRICE_RULE")
data class PriceRuleModel(
    @EmbeddedId
    val id: PriceRuleModelId,

    @Column(name = "PRICE_FOR_30_M")
    val priceFor30m: Int
) {
    constructor(): this(
        id = PriceRuleModelId(),
        priceFor30m = 0
    )
}
