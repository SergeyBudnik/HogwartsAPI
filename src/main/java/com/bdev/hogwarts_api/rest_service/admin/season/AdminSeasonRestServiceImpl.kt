package com.bdev.hogwarts_api.rest_service.admin.season

import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import com.bdev.hogwarts_api.data.dto.season.SeasonInfo
import com.bdev.hogwarts_api.service.season.storage.SeasonStorageService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class AdminSeasonRestServiceImpl(
    private val seasonStorageService: SeasonStorageService
) : AdminSeasonRestService {
    @Transactional(readOnly = true)
    override fun getAll(userInfo: MunicipaliUserInfo): ResponseEntity<*> {
        return ResponseEntity.ok(
            seasonStorageService.getAll()
        )
    }

    @Transactional(readOnly = true)
    override fun getById(userInfo: MunicipaliUserInfo, id: Long): ResponseEntity<*> {
        return if (seasonStorageService.exists(id = id)) {
            return ResponseEntity.ok(
                seasonStorageService.getById(id = id)
            )
        } else {
            ResponseEntity.notFound().build<Any>()
        }
    }

    @Transactional(readOnly = false)
    override fun create(userInfo: MunicipaliUserInfo, seasonInfo: SeasonInfo): ResponseEntity<*> {
        return seasonStorageService
            .create(seasonInfo = seasonInfo)
            .let { id -> ResponseEntity.ok(id) }
    }

    @Transactional(readOnly = false)
    override fun update(userInfo: MunicipaliUserInfo, id: Long, seasonInfo: SeasonInfo): ResponseEntity<*> {
        return if (seasonStorageService.exists(id = id)) {
            seasonStorageService.update(
                id = id,
                seasonInfo = seasonInfo
            )

            ResponseEntity.noContent().build<Any>()
        } else {
            ResponseEntity.notFound().build<Any>()
        }
    }

    @Transactional(readOnly = false)
    override fun activate(userInfo: MunicipaliUserInfo, id: Long): ResponseEntity<*> {
        return if (seasonStorageService.exists(id = id)) {
            seasonStorageService.getAll().forEach { season ->
                seasonStorageService.activate(
                    id = season.id,
                    active = season.id == id
                )
            }

            ResponseEntity.noContent().build<Any>()
        } else {
            ResponseEntity.notFound().build<Any>()
        }
    }

    @Transactional(readOnly = false)
    override fun delete(userInfo: MunicipaliUserInfo, id: Long): ResponseEntity<*> {
        return if (seasonStorageService.exists(id = id)) {
            seasonStorageService.delete(id = id)

            ResponseEntity.noContent().build<Any>()
        } else {
            ResponseEntity.notFound().build<Any>()
        }
    }
}
