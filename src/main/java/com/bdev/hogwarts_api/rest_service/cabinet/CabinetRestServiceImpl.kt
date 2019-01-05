package com.bdev.hogwarts_api.rest_service.cabinet

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.cabinet.CabinetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class CabinetRestServiceImpl : CabinetRestService {
    @Autowired
    private lateinit var cabinetService: CabinetService

    @Transactional(readOnly = true)
    override fun getAllCabinets(userInfo: MunicipaliUserInfo): List<Cabinet> {
        return cabinetService.getAllCabinets()
    }

    @Transactional(readOnly = true)
    override fun getCabinet(
            userInfo: MunicipaliUserInfo,
            cabinetId: Long
    ): Cabinet {
        return cabinetService
                .getCabinet(cabinetId)
                ?: throw HttpEntityNotFoundException("Cabinet with id '%d' does not exist", cabinetId)
    }

    @Transactional
    override fun saveCabinet(
            userInfo: MunicipaliUserInfo,
            cabinet: Cabinet
    ): Long {
        if (cabinet.id != null) {
            throw HttpEntityIllegalStateException("Cabinet id should be null during creation")
        }

        return cabinetService.saveCabinet(cabinet)
    }

    @Transactional
    override fun editCabinet(
            userInfo: MunicipaliUserInfo,
            cabinet: Cabinet
    ) {
        val cabinetId = cabinet.id ?: throw HttpEntityIllegalStateException("Cabinet id should be non-null during edit")

        if (!cabinetService.exists(cabinetId)) {
            throw HttpEntityNotFoundException("Cabinet with id '%d' does not exist", cabinetId)
        }

        cabinetService.editCabinet(cabinet)
    }

    @Transactional
    override fun deleteCabinet(
            userInfo: MunicipaliUserInfo,
            cabinetId: Long
    ) {
        if (!cabinetService.exists(cabinetId)) {
            throw HttpEntityNotFoundException("Cabinet with id '%d' does not exist", cabinetId)
        }

        cabinetService.deleteCabinet(cabinetId)
    }
}
