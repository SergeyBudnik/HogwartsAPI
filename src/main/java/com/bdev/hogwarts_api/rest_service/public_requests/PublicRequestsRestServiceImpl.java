package com.bdev.hogwarts_api.rest_service.public_requests;

import com.bdev.hogwarts_api.data.dto.Age;
import com.bdev.hogwarts_api.data.dto.EducationLevel;
import com.bdev.hogwarts_api.data.dto.student.Student;
import com.bdev.hogwarts_api.data.dto.student.StudentReferralSource;
import com.bdev.hogwarts_api.data.dto.student.StudentStatus;
import com.bdev.hogwarts_api.data.dto.student.StudentStatusType;
import com.bdev.hogwarts_api.service.student.StudentService;
import com.bdev.hogwarts_api.service.student_status.StudentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Service
public class PublicRequestsRestServiceImpl implements PublicRequestsRestService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentStatusService studentStatusService;

    @Override
    @Transactional
    public void addRequest(String name, String phone) {
        Student student = Student
                .builder()
                .groupIds(emptyList())
                .name(name)
                .statusType(StudentStatusType.REQUEST)
                .phones(singletonList(phone))
                .emails(emptyList())
                .educationLevel(EducationLevel.UNKNOWN)
                .age(Age.UNKNOWN)
                .referralSource(StudentReferralSource.VK_ADS)
                .build();

        long studentId = studentService.createStudent(student);

        studentStatusService.changeStudentStatus(StudentStatus
                .builder()
                .studentId(studentId)
                .creationTime(new Date().getTime())
                .actionTime(-1L)
                .status(StudentStatusType.REQUEST)
                .build()
        );
    }
}
