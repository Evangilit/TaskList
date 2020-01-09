package ru.seva.tasklist.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.seva.tasklist.model.Task;
import ru.seva.tasklist.model.User;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {
    Task findByTarget(String target);

    @Query(value = "select * from tasks t inner join users u on t.user_id = u.id where u.username=:authorName order by t.date", nativeQuery = true)
    List<Task> findByAuthorName(String authorName);

    @Query(value = "select * from tasks t inner join users u on t.user_id = u.id where u.username=:authorName and t.target=:target order by t.date", nativeQuery = true)
    Task findByTargetAndAuthor(String target, String authorName);
}
