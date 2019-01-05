package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel
import org.springframework.data.jpa.repository.JpaRepository

interface CabinetDao : JpaRepository<CabinetModel, Long>
