package com.bdev.hogwarts_api.rest_service.student_status;

import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import com.bdev.hogwarts_api.data.dto.student.StudentStatus;

import java.util.List;

public interface StudentStatusRestService {
    List<StudentStatus> getStudentStatuses(MunicipaliUserInfo userInfo, long studentId);
}
