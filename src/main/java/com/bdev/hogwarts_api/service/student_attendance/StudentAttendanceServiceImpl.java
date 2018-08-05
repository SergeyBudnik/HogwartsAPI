package com.bdev.hogwarts_api.service.student_attendance;

import com.bdev.hogwarts_api.dao.StudentAttendanceDao;
import com.bdev.hogwarts_api.data.converter.student_attendance.StudentAttendanceDtoConverter;
import com.bdev.hogwarts_api.data.converter.student_attendance.StudentAttendanceModelConverter;
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;
import com.bdev.hogwarts_api.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    @Autowired
    private StudentAttendanceDao studentAttendanceDao;

    @Autowired
    private StudentService studentService;

    @Override
    public boolean exists(long attendanceId) {
        return studentAttendanceDao.exists(attendanceId);
    }

    @Override
    public List<StudentAttendance> getAllAttendances() {
        return studentAttendanceDao
                .findAll()
                .stream()
                .map(StudentAttendanceModelConverter::convert)
                .collect(toList());
    }

    @Override
    public List<StudentAttendance> getAttendances(long studentId) {
        if (!studentService.exists(studentId)) {
            throw new RuntimeException(format("Student with id '%d' does not exist", studentId));
        }

        return studentAttendanceDao
                .getAllByStudentId(studentId)
                .stream()
                .map(StudentAttendanceModelConverter::convert)
                .collect(toList());
    }

    @Override
    public long addAttendance(StudentAttendance attendance) {
        if (!studentService.exists(attendance.getStudentId())) {
            throw new RuntimeException(format("Student with id '%d' does not exist", attendance.getStudentId()));
        }

        return studentAttendanceDao.save(StudentAttendanceDtoConverter.convert(attendance)).getId();
    }

    @Override
    public void deleteAttendance(long attendanceId) {
        if (!studentAttendanceDao.exists(attendanceId)) {
            throw new RuntimeException(format("Student attendance with id '%d' does not exist", attendanceId));
        }

        studentAttendanceDao.delete(attendanceId);
    }
}
