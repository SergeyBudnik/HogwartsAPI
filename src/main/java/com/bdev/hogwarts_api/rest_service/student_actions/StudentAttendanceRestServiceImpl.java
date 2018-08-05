package com.bdev.hogwarts_api.rest_service.student_actions;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException;
import com.bdev.hogwarts_api.service.student.StudentService;
import com.bdev.hogwarts_api.service.student_attendance.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentAttendanceRestServiceImpl implements StudentAttendanceRestService {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @Transactional(readOnly = true)
    @Override
    public List<StudentAttendance> getAllAttendances(MunicipaliUserInfo userInfo) {
        return studentAttendanceService.getAllAttendances();
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentAttendance> getAttendances(MunicipaliUserInfo userInfo, long studentId) {
        if (!studentService.getStudentById(studentId).isPresent()) {
            throw new HttpEntityNotFoundException("Student with id '%d' does not exist", studentId);
        }

        return studentAttendanceService.getAttendances(studentId);
    }

    @Transactional
    @Override
    public long addAttendance(MunicipaliUserInfo userInfo, StudentAttendance attendance) {
        if (!studentService.getStudentById(attendance.getStudentId()).isPresent()) {
            throw new HttpEntityNotFoundException("Student with id '%d' does not exist", attendance.getStudentId());
        }

        return studentAttendanceService.addAttendance(attendance);
    }

    @Transactional
    @Override
    public void deleteAttendance(MunicipaliUserInfo userInfo, long attendanceId) {
        if (!studentAttendanceService.exists(attendanceId)) {
            throw new HttpEntityNotFoundException("Student attendance with id '%d' does not exist", attendanceId);
        }

        studentAttendanceService.deleteAttendance(attendanceId);
    }
}
