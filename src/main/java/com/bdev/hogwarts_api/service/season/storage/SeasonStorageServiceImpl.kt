package com.bdev.hogwarts_api.service.season.storage

import com.bdev.hogwarts_api.dao.SeasonDao
import com.bdev.hogwarts_api.data.converter.season.SeasonDtoConverter
import com.bdev.hogwarts_api.data.converter.season.SeasonModelConverter
import com.bdev.hogwarts_api.data.dto.season.Season
import com.bdev.hogwarts_api.data.dto.season.SeasonInfo
import com.bdev.hogwarts_api.data.model.season.SeasonModel
import com.bdev.hogwarts_api.exceptions.domain.AppEntityNotFound
import org.springframework.stereotype.Service

@Service
class SeasonStorageServiceImpl(
    private val seasonDao: SeasonDao
) : SeasonStorageService {
    override fun exists(id: Long): Boolean {
        return seasonDao.exists(id)
    }

    override fun getAll(): List<Season> {
        return seasonDao
            .findAll()
            .map { seasonModel -> SeasonModelConverter.convert(model = seasonModel) }
    }

    override fun getById(id: Long): Season? {
        return seasonDao
            .getOne(id)
            ?.let { seasonModel -> SeasonModelConverter.convert(model = seasonModel) }
    }

    override fun create(seasonInfo: SeasonInfo): Long {
        return seasonDao.save(
            SeasonDtoConverter.convert(dto = seasonInfo)
        ).id!!
    }

    override fun update(id: Long, seasonInfo: SeasonInfo) {
        return ifExists(
            id = id,
            action = { seasonModel ->
                seasonDao.save(
                    SeasonDtoConverter.convert(
                        dto = Season(
                            id = id,
                            info = seasonInfo,
                            active = seasonModel.active
                        )
                    )
                )
            }
        )
    }

    override fun activate(id: Long, active: Boolean) {
        return ifExists(
            id = id,
            action = { seasonModel ->
                seasonDao.save(seasonModel.copy(active = active))
            }
        )
    }

    override fun delete(id: Long) {
        return ifExists(
            id = id,
            action = { seasonDao.delete(id) }
        )
    }

    private fun ifExists(id: Long, action: (SeasonModel) -> Unit) {
        val model = seasonDao.findOne(id)

        if (model != null) {
            action(model)
        } else {
            throw AppEntityNotFound(id = id.toString(), type = "Season")
        }
    }
}
