package com.bdev.hogwarts_api.rest_service.student;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.Student;
import com.bdev.hogwarts_api.data.dto.student.StudentStatus;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityIllegalStateException;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.student.StudentService;
import com.bdev.hogwarts_api.service.student_status.StudentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentRestServiceImpl implements StudentRestService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentStatusService studentStatusService;

    @Transactional(readOnly = true)
    @Override
    public List<Student> getAllStudents(
            MunicipaliUserInfo userInfo
    ) {
        return studentService.getAllStudents();
    }

    @Transactional(readOnly = true)
    @Override
    public Student getStudentById(
            MunicipaliUserInfo userInfo,
            long studentId
    ) {
        return studentService
                .getStudentById(studentId)
                .orElseThrow(() -> new HttpEntityNotFoundException("Student with id '%d' does not exist", studentId));
    }

    @Transactional
    @Override
    public long createStudent(
            MunicipaliUserInfo userInfo,
            Student student
    ) {
        if (student.getId() != null) {
            throw new HttpEntityIllegalStateException("Student id should be null during creation");
        }
        
        long studentId = studentService.createStudent(student);

        studentStatusService.changeStudentStatus(StudentStatus
                .builder()
                .studentId(studentId)
                .creationTime(new Date().getTime())
                .actionTime(-1L)
                .status(student.getStatusType())
                .build()
        );

        return studentId;
    }

    @Transactional
    @Override
    public void editStudent(
            MunicipaliUserInfo userInfo,
            Student student
    ) {
        if (student.getId() == null) {
            throw new HttpEntityIllegalStateException("Student id should be non-null during edit");
        }

        if (!studentService.exists(student.getId())) {
            throw new HttpEntityNotFoundException("Student with id '%d' does not exist", student.getId());
        }

        studentService.editStudent(student);
    }

    @Transactional
    @Override
    public void deleteStudent(
            MunicipaliUserInfo userInfo,
            long studentId
    ) {
        if (!studentService.exists(studentId)) {
            throw new HttpEntityNotFoundException("Student with id '%d' does not exist", studentId);
        }

        studentService.deleteStudent(studentId);
    }
}
