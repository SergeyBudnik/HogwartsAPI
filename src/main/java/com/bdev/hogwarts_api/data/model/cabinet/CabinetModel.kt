package com.bdev.hogwarts_api.data.model.cabinet

import com.bdev.hogwarts_api.data.dto.cabinet.CabinetType
import javax.persistence.*

@Entity
@Table(name = "HG_CABINET")
open class CabinetModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "NAME")
    var name: String? = null

    @Column(name = "CABINET_TYPE")
    @Enumerated(EnumType.STRING)
    var cabinetType: CabinetType? = null
}
