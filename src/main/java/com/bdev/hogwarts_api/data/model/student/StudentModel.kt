package com.bdev.hogwarts_api.data.model.student

import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.bdev.hogwarts_api.data.dto.Age

import javax.persistence.*

@Entity
@Table(name = "HG_STUDENT")
open class StudentModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "NAME")
    var name: String? = null
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
    var phones: MutableList<StudentPhoneModel> = ArrayList()
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
    var emails: MutableList<StudentEmailModel> = ArrayList()
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
    var studentGroups: MutableList<StudentGroupReferenceModel> = ArrayList()
    @Column(name = "VK_LINK")
    var vkLink: String? = null
    @Column(name = "EDUCATION_LEVEL")
    @Enumerated(EnumType.STRING)
    var educationLevel: EducationLevel = EducationLevel.UNKNOWN
    @Column(name = "AGE")
    @Enumerated(EnumType.STRING)
    var age: Age = Age.UNKNOWN
}
