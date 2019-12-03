package com.bdev.hogwarts_api.data.model.person

import javax.persistence.*

@Entity
@Table(name = "HG_PERSON")
open class PersonModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "NAME", length = 256, nullable = false)
    var name: String = ""

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = [CascadeType.ALL], orphanRemoval = true)
    var contacts: MutableList<PersonContactModel> = ArrayList()
}
