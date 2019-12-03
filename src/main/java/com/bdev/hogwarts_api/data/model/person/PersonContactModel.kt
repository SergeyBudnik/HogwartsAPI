package com.bdev.hogwarts_api.data.model.person

import com.bdev.hogwarts_api.data.dto.person.PersonContactType
import javax.persistence.*

@Entity
@Table(name = "HG_PERSON_CONTACT")
open class PersonContactModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    var person: PersonModel = PersonModel()

    @Column(name = "VALUE", length = 256, nullable = false)
    var value: String = ""
    @Column(name = "NAME", length = 256, nullable = false)
    var name: String = ""
    @Column(name = "TYPE", length = 32, nullable = false)
    var type: String = PersonContactType.PHONE.id
}
