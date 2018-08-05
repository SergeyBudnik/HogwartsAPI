package com.bdev.hogwarts_api.service.teacher;

import com.bdev.hogwarts_api.dao.TeacherDao;
import com.bdev.hogwarts_api.data.converter.teacher.TeacherDtoConverter;
import com.bdev.hogwarts_api.data.converter.teacher.TeacherModelConverter;
import com.bdev.hogwarts_api.data.dto.teacher.Teacher;
import com.bdev.hogwarts_api.utils.EncodingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public boolean exists(long id) {
        return teacherDao.exists(id);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.findAll()
                .stream()
                .map(TeacherModelConverter::convert)
                .collect(toList());
    }

    @Override
    public Optional<Teacher> getTeacherById(long id) {
        return Optional.ofNullable(
                teacherDao.findOne(id)
        ).map(TeacherModelConverter::convert);
    }

    @Override
    public Optional<Teacher> getTeacherByLogin(String login) {
        return Optional.ofNullable(
                teacherDao.findFirstByLogin(EncodingUtils.toBase64(login))
        ).map(TeacherModelConverter::convert);
    }

    @Override
    public long createTeacher(Teacher teacher) {
        if (teacher.getId() != null) {
            throw new RuntimeException("Teacher id should be null during creation");
        }

        return teacherDao.save(TeacherDtoConverter.convert(teacher)).getId();
    }

    @Override
    public void editTeacher(Teacher teacher) {
        if (!teacherDao.exists(teacher.getId())) {
            throw new RuntimeException(format("Teacher with id '%d' does not exist", teacher.getId()));
        }

        teacherDao.save(TeacherDtoConverter.convert(teacher));
    }

    @Override
    public void deleteTeacher(long id) {
        if (!teacherDao.exists(id)) {
            throw new RuntimeException(format("Teacher with id '%d' does not exist", id));
        }

        teacherDao.delete(id);
    }
}
