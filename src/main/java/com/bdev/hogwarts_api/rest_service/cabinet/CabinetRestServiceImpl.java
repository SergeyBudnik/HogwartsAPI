package com.bdev.hogwarts_api.rest_service.cabinet;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.cabinet.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CabinetRestServiceImpl implements CabinetRestService {
    @Autowired
    private CabinetService cabinetService;

    @Transactional(readOnly = true)
    @Override
    public List<Cabinet> getAllCabinets(
            MunicipaliUserInfo userInfo
    ) {
        return cabinetService.getAllCabinets();
    }

    @Transactional(readOnly = true)
    @Override
    public Cabinet getCabinet(
            MunicipaliUserInfo userInfo,
            long cabinetId
    ) {
        return cabinetService.getCabinet(cabinetId)
                .orElseThrow(() -> new HttpEntityNotFoundException("Cabinet with id '%d' does not exist", cabinetId));
    }

    @Transactional
    @Override
    public long saveCabinet(
            MunicipaliUserInfo userInfo,
            Cabinet cabinet
    ) {
        if (cabinet.getId() != null) {
            throw new HttpEntityIllegalStateException("Cabinet id should be null during creation");
        }

        return cabinetService.saveCabinet(cabinet);
    }

    @Transactional
    @Override
    public void editCabinet(
            MunicipaliUserInfo userInfo,
            Cabinet cabinet
    ) {
        if (cabinet.getId() == null) {
            throw new HttpEntityIllegalStateException("Cabinet id should be non-null during edit");
        }

        if (!cabinetService.exists(cabinet.getId())) {
            throw new HttpEntityNotFoundException("Cabinet with id '%d' does not exist", cabinet.getId());
        }

        cabinetService.editCabinet(cabinet);
    }

    @Transactional
    @Override
    public void deleteCabinet(
            MunicipaliUserInfo userInfo,
            long cabinetId
    ) {
        if (!cabinetService.exists(cabinetId)) {
            throw new HttpEntityNotFoundException("Cabinet with id '%d' does not exist", cabinetId);
        }

        cabinetService.deleteCabinet(cabinetId);
    }
}
