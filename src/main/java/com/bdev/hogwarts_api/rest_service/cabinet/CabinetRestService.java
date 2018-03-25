package com.bdev.hogwarts_api.rest_service.cabinet;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet;

import java.util.List;

public interface CabinetRestService {
    List<Cabinet> getAllCabinets(MunicipaliUserInfo userInfo);
    Cabinet getCabinet(MunicipaliUserInfo userInfo, long cabinetId);
    long saveCabinet(MunicipaliUserInfo userInfo, Cabinet cabinet);
    void editCabinet(MunicipaliUserInfo userInfo, Cabinet cabinet);
    void deleteCabinet(MunicipaliUserInfo userInfo, long cabinetId);
}
