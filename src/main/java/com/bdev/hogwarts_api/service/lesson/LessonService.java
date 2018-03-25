package com.bdev.hogwarts_api.service.lesson;

import com.bdev.hogwarts_api.data.dto.group.GroupLesson;

import java.util.List;
import java.util.Optional;

public interface LessonService {
    List<GroupLesson> getAllLessons();
    List<GroupLesson> getGroupLessons(long groupId);
    Optional<GroupLesson> getLesson(long id);
    long createLesson(GroupLesson lesson);
}
