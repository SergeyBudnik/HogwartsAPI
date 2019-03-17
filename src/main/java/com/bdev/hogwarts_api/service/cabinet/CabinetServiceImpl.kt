package com.bdev.hogwarts_api.service.cabinet

import com.bdev.hogwarts_api.dao.CabinetDao
import com.bdev.hogwarts_api.data.converter.cabinet.CabinetDtoConverter
import com.bdev.hogwarts_api.data.converter.cabinet.CabinetModelConverter
import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.String.format

@Service
open class CabinetServiceImpl : CabinetService {
    @Autowired
    private lateinit var cabinetDao: CabinetDao

    override fun getAllCabinets(): List<Cabinet> {
        return cabinetDao.findAll().map { CabinetModelConverter.convert(it) }
    }

    override fun exists(id: Long): Boolean {
        return cabinetDao.exists(id)
    }

    override fun getCabinet(id: Long): Cabinet? {
        return cabinetDao
                .findOne(id)
                ?.let { CabinetModelConverter.convert(it) }
    }

    override fun saveCabinet(cabinet: Cabinet): Long {
        if (cabinet.id != null) {
            throw RuntimeException("Cabinet id should be null during creation")
        }

        return cabinetDao.save(CabinetDtoConverter.convert(cabinet)).id ?: throw RuntimeException()
    }

    override fun editCabinet(cabinet: Cabinet) {
        if (!cabinetDao.exists(cabinet.id)) {
            throw RuntimeException(format("Cabinet with id '%d' does not exist", cabinet.id))
        }

        cabinetDao.save(CabinetDtoConverter.convert(cabinet))
    }

    override fun deleteCabinet(id: Long) {
        if (!cabinetDao.exists(id)) {
            throw RuntimeException(format("Cabinet with id '%d' does not exist", id))
        }

        cabinetDao.delete(id)
    }
}
