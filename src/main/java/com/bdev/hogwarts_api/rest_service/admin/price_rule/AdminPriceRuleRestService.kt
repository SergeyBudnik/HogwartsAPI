package com.bdev.hogwarts_api.rest_service.admin.price_rule

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.price_rule.PriceRule
import com.bdev.hogwarts_api.data.dto.price_rule.PriceRuleId
import com.bdev.hogwarts_api.service.lesson_type.LessonTypeStorageService
import com.bdev.hogwarts_api.service.price_rule.PriceRuleStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface AdminPriceRuleRestService {
    fun getAll(
        userInfo: MunicipaliUserInfo
    ): ResponseEntity<*>

    fun set(
        userInfo: MunicipaliUserInfo,
        priceRule: PriceRule
    ): ResponseEntity<*>

    fun delete(
        userInfo: MunicipaliUserInfo,
        priceRuleId: PriceRuleId
    ): ResponseEntity<*>
}

@Service
open class AdminPriceRuleRestServiceImpl @Autowired constructor(
    private val priceRuleStorageService: PriceRuleStorageService,
    private val lessonTypeStorageService: LessonTypeStorageService
): AdminPriceRuleRestService {
    @Transactional(readOnly = true)
    override fun getAll(
        userInfo: MunicipaliUserInfo
    ): ResponseEntity<*> {
        return ResponseEntity.ok(priceRuleStorageService.getAll())
    }

    @Transactional(readOnly = false)
    override fun set(
        userInfo: MunicipaliUserInfo,
        priceRule: PriceRule
    ): ResponseEntity<*> {
        val lessonTypeExists = lessonTypeStorageService.exists(priceRule.id.lessonTypeId)

        return if (lessonTypeExists) {
            priceRuleStorageService.set(priceRule = priceRule)

            ResponseEntity
                .ok("Success")
        } else {
            ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Lesson type does not exist by id '${priceRule.id.lessonTypeId}'")
        }
    }

    @Transactional(readOnly = false)
    override fun delete(
        userInfo: MunicipaliUserInfo,
        priceRuleId: PriceRuleId
    ): ResponseEntity<*> {
        val deleted = priceRuleStorageService.delete(
            priceRuleId = priceRuleId
        )

        return if (deleted) {
            ResponseEntity.ok("Success")
        } else {
            ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Price rule does not exist by provided id")
        }
    }
}
