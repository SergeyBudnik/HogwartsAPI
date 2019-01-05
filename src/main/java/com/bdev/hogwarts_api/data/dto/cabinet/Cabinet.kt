package com.bdev.hogwarts_api.data.dto.cabinet

class Cabinet(
    var id: Long? = null,
    var name: String = "",
    val cabinetType: CabinetType = CabinetType.HOGWARTS_PRIMORSKAYA
)
