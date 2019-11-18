package com.bdev.hogwarts_api.data.model.staff

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "HG_STAFF_MEMBER")
open class StaffMemberModel {
    @Id
    @Column(name = "LOGIN", length = 128, nullable = false)
    var login: String = ""

    @Column(name = "PERSON_ID", nullable = false)
    var personId: Long = 0L
}
