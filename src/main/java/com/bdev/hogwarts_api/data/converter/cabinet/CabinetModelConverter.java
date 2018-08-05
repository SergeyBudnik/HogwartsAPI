package com.bdev.hogwarts_api.data.converter.cabinet;

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet;
import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel;
import com.bdev.hogwarts_api.utils.EncodingUtils;

import static com.bdev.hogwarts_api.utils.EncodingUtils.fromBase64;

public class CabinetModelConverter {
    public static Cabinet convert(CabinetModel cabinetModel) {
        return Cabinet.builder()
                .id(cabinetModel.getId())
                .name(fromBase64(cabinetModel.getName()))
                .cabinetType(cabinetModel.getCabinetType())
                .build();
    }
}
