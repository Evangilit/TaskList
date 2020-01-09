package ru.seva.tasklist.service;


import ru.seva.tasklist.model.Task;

import java.util.List;

public interface TaskService {

    Iterable<Task> allTasks();
    void add(Task task);
    void update(Task task);
    void delete(Task task);
    void delete(String target);
    Task findByTarget(String target);
    Task findById(Long id);
    List<Task> findByAuthorName(String authorName);
    Task findByTargetAndAuthor(String target, String authorName);
}
