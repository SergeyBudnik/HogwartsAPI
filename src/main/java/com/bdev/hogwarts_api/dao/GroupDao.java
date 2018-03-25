package com.bdev.hogwarts_api.dao;

import com.bdev.hogwarts_api.data.model.group.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDao extends JpaRepository<GroupModel, Long> {
}
