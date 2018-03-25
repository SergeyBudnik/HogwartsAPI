package com.bdev.hogwarts_api.data.converter.cabinet;

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet;
import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel;

public class CabinetModelConverter {
    public static Cabinet convert(CabinetModel cabinetModel) {
        return Cabinet.builder()
                .id(cabinetModel.getId())
                .name(cabinetModel.getName())
                .cabinetType(cabinetModel.getCabinetType())
                .build();
    }
}
