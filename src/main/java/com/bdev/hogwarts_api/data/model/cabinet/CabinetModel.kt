package com.bdev.hogwarts_api.data.model.cabinet

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
}
