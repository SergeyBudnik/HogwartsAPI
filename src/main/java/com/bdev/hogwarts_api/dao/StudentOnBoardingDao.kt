package com.bdev.hogwarts_api.dao

import com.bdev.hogwarts_api.data.model.student.on_boarding.StudentOnBoardingModel
import org.springframework.data.jpa.repository.JpaRepository

interface StudentOnBoardingDao : JpaRepository<StudentOnBoardingModel, String>
