package com.bdev.hogwarts_api.rest_service.student_actions;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.StudentAttendance;
import com.bdev.hogwarts_api.data.dto.student.StudentPayment;

import java.util.List;

public interface StudentAttendanceRestService {
    List<StudentAttendance> getAllAttendances(MunicipaliUserInfo userInfo);
    List<StudentAttendance> getAttendances(MunicipaliUserInfo userInfo, long studentId);
    long addAttendance(MunicipaliUserInfo userInfo, StudentAttendance attendance);
    void deleteAttendance(MunicipaliUserInfo userInfo, long attendanceId);
}
