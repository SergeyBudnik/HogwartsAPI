package com.bdev.hogwarts_api.service.student;

import com.bdev.hogwarts_api.dao.StudentDao;
import com.bdev.hogwarts_api.dao.StudentStatusDao;
import com.bdev.hogwarts_api.data.converter.student.StudentDtoConverter;
import com.bdev.hogwarts_api.data.converter.student.StudentModelConverter;
import com.bdev.hogwarts_api.data.dto.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentStatusDao studentStatusDao;

    @Override
    public boolean exists(long studentId) {
        return studentDao.exists(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao
                .findAll()
                .stream()
                .map(it -> StudentModelConverter.convert(
                        it,
                        studentStatusDao.findTopByStudentIdOrderByCreationTimeDesc(it.getId())
                ))
                .collect(toList());
    }

    @Override
    public Optional<Student> getStudentById(long studentId) {
        return Optional
                .ofNullable(studentDao.getOne(studentId))
                .map(it -> StudentModelConverter.convert(
                        it,
                        studentStatusDao.findTopByStudentIdOrderByCreationTimeDesc(it.getId())
                ));
    }

    @Override
    public long createStudent(Student student) {
        if (student.getId() != null) {
            throw new RuntimeException("Student id should be null during creation");
        }

        return studentDao.save(StudentDtoConverter.convert(student)).getId();
    }

    @Override
    public void editStudent(Student student) {
        if (!studentDao.exists(student.getId())) {
            throw new RuntimeException(format("Student with id '%d' does not exist", student.getId()));
        }

        studentDao.save(StudentDtoConverter.convert(student));
    }

    @Override
    public void deleteStudent(long studentId) {
        if (!studentDao.exists(studentId)) {
            throw new RuntimeException(format("Student with id '%d' does not exist", studentId));
        }

        studentDao.delete(studentId);
    }
}
