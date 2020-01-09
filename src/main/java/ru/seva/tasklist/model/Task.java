package ru.seva.tasklist.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
@Table(name = "tasks", uniqueConstraints = { @UniqueConstraint(columnNames = {"target", "user_id"})})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String target;
    private String description;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Task() {
    }

    public Task(String target, String description, Date date, User user) {
        this.target = target;
        this.description = description;
        this.date = date;
        this.author = user;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", target='" + target + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    /* Fake data
    private static List<Task> fakeTasks = new ArrayList<>();
    static {
        Calendar calendar1 = new GregorianCalendar(2020, Calendar.DECEMBER, 29);
        Calendar calendar2 = new GregorianCalendar(2019, Calendar.DECEMBER, 31);
        Calendar calendar3 = new GregorianCalendar(2021, Calendar.JANUARY, 1);
        fakeTasks.add(new Task("Wash the cat", "No description, just wash him", new Date(calendar1.getTimeInMillis())));
        fakeTasks.add(new Task("Buy alcohol", "Too much as possible", new Date(calendar2.getTimeInMillis())));
        fakeTasks.add(new Task("Begin to search a job", "Finally its the right time", new Date(calendar3.getTimeInMillis())));
    }
    public static void addTask(Task task){
        fakeTasks.add(task);
    }

    public static List<Task> getFakeTasks() {
        return fakeTasks;
    }

    public static void setFakeTasks(List<Task> fakeTasks) {
        Task.fakeTasks = fakeTasks;
    }
    */
}
