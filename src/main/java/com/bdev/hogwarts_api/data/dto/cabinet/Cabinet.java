package com.bdev.hogwarts_api.data.dto.cabinet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class Cabinet {
    private Long id;
    @NonNull private String name;
    @NonNull private CabinetType cabinetType;

    @JsonCreator
    public static Cabinet create(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("cabinetType") CabinetType cabinetType
    ) {
        return Cabinet.builder()
                .id(id)
                .name(name)
                .cabinetType(cabinetType)
                .build();
    }
}
