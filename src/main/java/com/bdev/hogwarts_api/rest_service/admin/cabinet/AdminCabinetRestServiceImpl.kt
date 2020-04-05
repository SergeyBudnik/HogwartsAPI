package com.bdev.hogwarts_api.rest_service.admin.cabinet

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.cabinet.ExistingCabinet
import com.bdev.hogwarts_api.data.dto.cabinet.NewCabinet
import com.bdev.hogwarts_api.rest.common.CommonResponse
import com.bdev.hogwarts_api.service.cabinet.CabinetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class AdminCabinetRestServiceImpl @Autowired constructor(
    private val cabinetService: CabinetService
): AdminCabinetRestService {
    @Transactional(readOnly = true)
    override fun getAllCabinets(userInfo: MunicipaliUserInfo): ResponseEntity<Any> {
        val allCabinets = cabinetService.getAllCabinets()

        return CommonResponse.Success.withBody(allCabinets)
    }

    @Transactional(readOnly = true)
    override fun getCabinet(
            userInfo: MunicipaliUserInfo,
            cabinetId: Long
    ): ResponseEntity<Any> {
        val cabinet = cabinetService.getCabinet(cabinetId)

        return if (cabinet != null) {
            CommonResponse.Success.withBody(cabinet)
        } else {
            CommonResponse.NotFound.withMessage("Cabinet with id '$cabinetId' does not exist")
        }
    }

    @Transactional
    override fun saveCabinet(
            userInfo: MunicipaliUserInfo,
            cabinet: NewCabinet
    ): ResponseEntity<Any> {
        val id = cabinetService.saveCabinet(cabinet)

        return CommonResponse.Success.withBody(id)
    }

    @Transactional
    override fun editCabinet(
            userInfo: MunicipaliUserInfo,
            cabinet: ExistingCabinet
    ): ResponseEntity<Any> {
        return if (cabinetService.exists(cabinet.id)) {
            cabinetService.editCabinet(cabinet)

            CommonResponse.Success.empty()
        } else {
            CommonResponse.NotFound.withMessage("Cabinet with id '${cabinet.id}' does not exist")
        }
    }

    @Transactional
    override fun deleteCabinet(
            userInfo: MunicipaliUserInfo,
            cabinetId: Long
    ): ResponseEntity<Any> {
        return if (cabinetService.exists(cabinetId)) {
            cabinetService.deleteCabinet(cabinetId)

            CommonResponse.Success.empty()
        } else {
            CommonResponse.NotFound.withMessage("Cabinet with id '${cabinetId}' does not exist")
        }
    }
}
