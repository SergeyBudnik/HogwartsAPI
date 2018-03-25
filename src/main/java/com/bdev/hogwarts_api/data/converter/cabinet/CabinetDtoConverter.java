package com.bdev.hogwarts_api.data.converter.cabinet;

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet;
import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel;

public class CabinetDtoConverter {
    public static CabinetModel convert(Cabinet cabinet) {
        CabinetModel cabinetModel = new CabinetModel();

        cabinetModel.setId(cabinet.getId());
        cabinetModel.setName(cabinet.getName());
        cabinetModel.setCabinetType(cabinet.getCabinetType());

        return cabinetModel;
    }
}
