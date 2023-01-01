package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.season.SeasonModel
import org.springframework.data.jpa.repository.JpaRepository

interface SeasonDao : JpaRepository<SeasonModel, Long>
