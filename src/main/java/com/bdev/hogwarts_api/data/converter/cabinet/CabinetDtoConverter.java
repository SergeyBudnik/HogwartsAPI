package com.bdev.hogwarts_api.data.converter.cabinet;

import com.bdev.hogwarts_api.data.dto.cabinet.Cabinet;
import com.bdev.hogwarts_api.data.model.cabinet.CabinetModel;
import com.bdev.hogwarts_api.utils.EncodingUtils;

import static com.bdev.hogwarts_api.utils.EncodingUtils.toBase64;

public class CabinetDtoConverter {
    public static CabinetModel convert(Cabinet cabinet) {
        CabinetModel cabinetModel = new CabinetModel();

        cabinetModel.setId(cabinet.getId());
        cabinetModel.setName(toBase64(cabinet.getName()));
        cabinetModel.setCabinetType(cabinet.getCabinetType());

        return cabinetModel;
    }
}
