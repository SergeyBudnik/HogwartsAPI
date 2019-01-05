package com.bdev.hogwarts_api.data.model.teacher

import com.bdev.hogwarts_api.data.dto.teacher.TeacherType
import lombok.Data

import javax.persistence.*

@Data
@Entity
@Table(name = "HG_TEACHER")
open class TeacherModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "LOGIN")
    var login: String? = null
    @Column(name = "NAME")
    var name: String? = null
    @Column(name = "TEACHER_TYPE")
    @Enumerated(EnumType.STRING)
    var type: TeacherType? = null
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = [CascadeType.ALL], orphanRemoval = true)
    var emails: MutableList<TeacherEmailModel>? = null
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = [CascadeType.ALL], orphanRemoval = true)
    var phones: MutableList<TeacherPhoneModel>? = null
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = [CascadeType.ALL], orphanRemoval = true)
    var availability: MutableList<TeacherAvailabilityModel>? = null
}
