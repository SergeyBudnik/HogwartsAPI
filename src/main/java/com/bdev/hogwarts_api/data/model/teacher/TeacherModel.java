package com.bdev.hogwarts_api.data.model.teacher;

import com.bdev.hogwarts_api.data.dto.teacher.TeacherType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "HG_TEACHER")
public class TeacherModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TEACHER_TYPE")
    @Enumerated(EnumType.STRING)
    private TeacherType type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherEmailModel> emails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherPhoneModel> phones;
}
