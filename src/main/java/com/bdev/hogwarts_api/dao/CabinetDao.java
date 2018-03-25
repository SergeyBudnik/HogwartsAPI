package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinetDao extends JpaRepository<CabinetModel, Long> {
}
