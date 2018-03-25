package com.bdev.hogwarts_api.data.model.student;

import com.bdev.hogwarts_api.data.dto.EducationLevel;
import com.bdev.hogwarts_api.data.dto.Age;
import com.bdev.hogwarts_api.data.dto.student.StudentReferralSource;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "HG_STUDENT")
public class StudentModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentPhoneModel> phones;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentEmailModel> emails;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentGroupReferenceModel> groupIds;
    @Column(name = "EDUCATION_LEVEL")
    private EducationLevel educationLevel;
    @Column(name = "AGE")
    private Age age;
    @Column(name = "REFERRAL_SOURCE")
    private StudentReferralSource referralSource;

}
