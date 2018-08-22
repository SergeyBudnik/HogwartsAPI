package com.bdev.hogwarts_api.data.model.cabinet;

import com.bdev.hogwarts_api.data.dto.cabinet.CabinetType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HG_CABINET")
public class CabinetModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "CABINET_TYPE")
    @Enumerated(EnumType.STRING)
    private CabinetType cabinetType;
}
