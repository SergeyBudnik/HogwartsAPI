package com.bdev.hogwarts_api.service.lesson;

import com.bdev.hogwarts_api.data.dto.group.GroupLesson;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    @Override
    public List<GroupLesson> getAllLessons() {
        return null;
    }

    @Override
    public List<GroupLesson> getGroupLessons(long groupId) {
        return null;
    }

    @Override
    public Optional<GroupLesson> getLesson(long id) {
        return Optional.empty();
    }

    @Override
    public long createLesson(GroupLesson lesson) {
        return 0;
    }
}
