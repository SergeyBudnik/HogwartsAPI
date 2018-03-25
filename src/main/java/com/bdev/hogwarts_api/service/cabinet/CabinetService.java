package com.bdev.hogwarts_api.service.cabinet;

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet;

import java.util.List;
import java.util.Optional;

public interface CabinetService {
    boolean exists(long id);
    List<Cabinet> getAllCabinets();
    Optional<Cabinet> getCabinet(long id);
    long saveCabinet(Cabinet cabinet);
    void editCabinet(Cabinet cabinet);
    void deleteCabinet(long id);
}
