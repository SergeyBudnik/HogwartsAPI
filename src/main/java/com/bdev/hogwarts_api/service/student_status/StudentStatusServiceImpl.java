package com.bdev.hogwarts_api.service.student_status;

import com.bdev.hogwarts_api.dao.StudentDao;
import com.bdev.hogwarts_api.dao.StudentStatusDao;
import com.bdev.hogwarts_api.data.converter.student_status.StudentStatusDtoConverter;
import com.bdev.hogwarts_api.data.converter.student_status.StudentStatusModelConverter;
import com.bdev.hogwarts_api.data.dto.student.StudentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class StudentStatusServiceImpl implements StudentStatusService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentStatusDao studentStatusDao;

    @Override
    public List<StudentStatus> getAllStudentStatuses(long studentId) {
        return studentStatusDao
                .findAllByStudentId(studentId)
                .stream()
                .map(StudentStatusModelConverter::convert)
                .sorted(comparing(StudentStatus::getCreationTime))
                .collect(toList());
    }

    @Override
    public Optional<StudentStatus> getStudentStatus(long studentId) {
        return Optional
                .ofNullable(studentStatusDao.findTopByStudentIdOrderByCreationTimeDesc(studentId))
                .map(StudentStatusModelConverter::convert);
    }

    @Override
    public void changeStudentStatus(StudentStatus studentStatus) {
        if (studentStatus.getId() != null) {
            throw new RuntimeException("Student status id should be null during creation");
        }

        if (!studentDao.exists(studentStatus.getStudentId())) {
            throw new RuntimeException(format("Student with id '%d' does not exist", studentStatus.getStudentId()));
        }

        studentStatusDao.save(StudentStatusDtoConverter.convert(studentStatus));
    }
}
