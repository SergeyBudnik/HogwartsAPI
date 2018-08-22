package com.bdev.hogwarts_api.data.model.group;

import com.bdev.hogwarts_api.data.dto.EducationLevel;
import com.bdev.hogwarts_api.data.dto.Age;
import com.bdev.hogwarts_api.data.dto.group.GroupType;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "HG_GROUP")
public class GroupModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CABINET_ID")
    private long cabinetId;

    @Column(name = "MANAGER_ID")
    private long managerId;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "GROUP_TYPE")
    @Enumerated(EnumType.STRING)
    private GroupType type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<GroupLessonModel> lessons;

    @Column(name = "AGE")
    @Enumerated(EnumType.STRING)
    private Age age;

    @Column(name = "EDUCATION_LEVEL")
    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Column(name = "COLOR")
    private String color;
}
