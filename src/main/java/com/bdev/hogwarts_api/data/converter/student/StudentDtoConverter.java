package com.bdev.hogwarts_api.data.converter.student;

import com.bdev.hogwarts_api.data.dto.student.Student;
import com.bdev.hogwarts_api.data.model.student.StudentEmailModel;
import com.bdev.hogwarts_api.data.model.student.StudentGroupReferenceModel;
import com.bdev.hogwarts_api.data.model.student.StudentModel;
import com.bdev.hogwarts_api.data.model.student.StudentPhoneModel;

import static java.util.stream.Collectors.toList;

public class StudentDtoConverter {
    public static StudentModel convert(Student student) {
        StudentModel studentModel = new StudentModel();
        
        studentModel.setId(student.getId());
        studentModel.setGroupIds(student.getGroupIds().stream().map(it -> convertGroupIds(studentModel, it)).collect(toList()));
        studentModel.setName(student.getName());
        studentModel.setPhones(student.getPhones().stream().map(it -> convertPhone(studentModel, it)).collect(toList()));
        studentModel.setEmails(student.getEmails().stream().map(it -> convertEmail(studentModel, it)).collect(toList()));
        studentModel.setEducationLevel(student.getEducationLevel());
        studentModel.setAge(student.getAge());
        studentModel.setReferralSource(student.getReferralSource());
        
        return studentModel;
    }

    private static StudentGroupReferenceModel convertGroupIds(StudentModel studentModel, long groupId) {
        StudentGroupReferenceModel studentGroupReferenceModel = new StudentGroupReferenceModel();

        studentGroupReferenceModel.setGroupId(groupId);
        studentGroupReferenceModel.setStudent(studentModel);

        return studentGroupReferenceModel;
    }
    
    private static StudentEmailModel convertEmail(StudentModel studentModel, String email) {
        StudentEmailModel studentEmailModel = new StudentEmailModel();
        
        studentEmailModel.setStudent(studentModel);
        studentEmailModel.setValue(email);
        
        return studentEmailModel;
    }

    private static StudentPhoneModel convertPhone(StudentModel studentModel, String phone) {
        StudentPhoneModel studentPhoneModel = new StudentPhoneModel();

        studentPhoneModel.setStudent(studentModel);
        studentPhoneModel.setValue(phone);

        return studentPhoneModel;
    }
}
