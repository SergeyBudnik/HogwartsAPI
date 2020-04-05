package com.bdev.hogwarts_api.service.cabinet

import com.bdev.hogwarts_api.dao.CabinetDao
import com.bdev.hogwarts_api.data.converter.cabinet.CabinetDtoConverter
import com.bdev.hogwarts_api.data.converter.cabinet.CabinetModelConverter
import com.bdev.hogwarts_api.data.dto.cabinet.ExistingCabinet
import com.bdev.hogwarts_api.data.dto.cabinet.NewCabinet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format

@Service
open class CabinetServiceImpl @Autowired constructor(
    private val cabinetDao: CabinetDao
): CabinetService {
    override fun getAllCabinets(): List<ExistingCabinet> {
        return cabinetDao.findAll().map { CabinetModelConverter.convert(it) }
    }

    override fun exists(id: Long): Boolean {
        return cabinetDao.exists(id)
    }

    override fun getCabinet(id: Long): ExistingCabinet? {
        return cabinetDao
                .findOne(id)
                ?.let { CabinetModelConverter.convert(it) }
    }

    override fun saveCabinet(cabinet: NewCabinet): Long {
        val cabinetModel = CabinetDtoConverter.convertNew(cabinet)

        return cabinetDao.save(cabinetModel).id!!
    }

    override fun editCabinet(cabinet: ExistingCabinet) {
        if (!cabinetDao.exists(cabinet.id)) {
            throw RuntimeException("Cabinet with id '${cabinet.id}' does not exist")
        }

        val cabinetModel = CabinetDtoConverter.convertExisting(cabinet)

        cabinetDao.save(cabinetModel)
    }

    override fun deleteCabinet(id: Long) {
        if (!cabinetDao.exists(id)) {
            throw RuntimeException(format("Cabinet with id '${id}' does not exist", id))
        }

        cabinetDao.delete(id)
    }
}
