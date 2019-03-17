package com.bdev.hogwarts_api.data.model.user_request

import javax.persistence.*

@Entity
@Table(name = "HG_USER_REQUEST")
class UserRequestModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "NAME")
    var name: String? = null
    @Column(name = "PHONE")
    var phone: String? = null
    @Column(name = "CREATION_DATE")
    var date: Long? = null
}
