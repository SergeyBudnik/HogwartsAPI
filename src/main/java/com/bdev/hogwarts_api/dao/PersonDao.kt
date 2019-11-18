package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.person.PersonModel
import org.springframework.data.jpa.repository.JpaRepository

interface PersonDao : JpaRepository<PersonModel, Long>
