package com.bdev.hogwarts_api.data.model.group

import com.bdev.hogwarts_api.data.dto.EducationLevel
import com.bdev.hogwarts_api.data.dto.Age
import com.bdev.hogwarts_api.data.dto.group.GroupType

import javax.persistence.*

@Entity
@Table(name = "HG_GROUP")
open class GroupModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "CABINET_ID")
    var cabinetId: Long? = null
    @Column(name = "MANAGER_ID")
    var managerId: Long? = null
    @Column(name = "BOOK_NAME")
    var bookName: String? = null
    @Column(name = "GROUP_TYPE")
    @Enumerated(EnumType.STRING)
    var type: GroupType? = null
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = [CascadeType.ALL], orphanRemoval = true)
    var lessons: MutableCollection<GroupLessonModel>? = null
    @Column(name = "AGE")
    @Enumerated(EnumType.STRING)
    var age: Age? = null
    @Column(name = "EDUCATION_LEVEL")
    @Enumerated(EnumType.STRING)
    var educationLevel: EducationLevel? = null
    @Column(name = "COLOR")
    var color: String? = null
}
