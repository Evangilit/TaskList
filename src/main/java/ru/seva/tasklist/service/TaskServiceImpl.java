package ru.seva.tasklist.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.seva.tasklist.model.Task;
import ru.seva.tasklist.repositories.TaskRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    public static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public Task findByTarget(String target){
        return taskRepo.findByTarget(target);
    }

    @Override
    public List<Task> findByAuthorName(String authorName) {
        return taskRepo.findByAuthorName(authorName);
    }

    @Override
    public Task findByTargetAndAuthor(String target, String authorName) {
        return taskRepo.findByTargetAndAuthor(target, authorName);
    }

    @Override
    public Task findById(Long id) {
        Optional<Task> optionalTask = taskRepo.findById(id);
        return optionalTask.orElse(null);
    }

    @Override
    public Iterable<Task> allTasks() {
        return taskRepo.findAll();
    }

    @Override
    public void add(Task task) {
        taskRepo.save(task);
    }

    @Override
    public void update(Task task) {
        taskRepo.save(task);
    }

    @Override
    public void delete(Task task) {
        log.warn("Deleting task {}", task.getTarget());
        taskRepo.delete(task);
    }

    @Override
    public void delete(String target) {
        log.warn("Deleting task {}", target);
        Task task = findByTarget(target);
        taskRepo.delete(task);
    }
}
