package com.bdev.hogwarts_api.rest.admin.price_rule

import com.bdev.hogwarts_api.data.dto.price_rule.PriceRule
import com.bdev.hogwarts_api.data.dto.price_rule.PriceRuleId
import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest_service.admin.price_rule.AdminPriceRuleRestService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/price-rules")
@Api(tags = ["Admin Price Rules"], description = "PROTECTED")
class AdminPriceRuleRest @Autowired constructor(
    private val adminPriceRuleRestService: AdminPriceRuleRestService
): CommonRest() {
    @GetMapping(value = ["/"])
    fun getAll(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String
    ): ResponseEntity<*> {
        return adminPriceRuleRestService.getAll(
            userInfo = getUserInfo(authToken = authToken)
        )
    }

    @PostMapping(value = ["/"])
    fun set(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @RequestBody priceRule: PriceRule
    ): ResponseEntity<*> {
        return adminPriceRuleRestService.set(
            userInfo = getUserInfo(authToken = authToken),
            priceRule = priceRule
        )
    }

    @DeleteMapping(value = ["/"])
    fun delete(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authToken: String,
        @RequestBody priceRuleId: PriceRuleId
    ): ResponseEntity<*> {
        return adminPriceRuleRestService.delete(
            userInfo = getUserInfo(authToken = authToken),
            priceRuleId = priceRuleId
        )
    }
}
