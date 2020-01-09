package ru.seva.tasklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.seva.tasklist.model.Task;
import ru.seva.tasklist.model.User;
import ru.seva.tasklist.service.TaskService;

import java.sql.Date;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User user,
            Map<String, Object> model){
        Iterable<Task> tasks = taskService.findByAuthorName(user.getUsername());
        model.put("tasks", tasks);
        return "main";
    }

    @GetMapping("/addtask")
    public String addTaskPage(){
        return "addtask";
    }

    @PostMapping("/addtask")
    public String addTask(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "target", required = true, defaultValue = "none") String target,
            @RequestParam(name = "description", required = true, defaultValue = "none") String description,
            @RequestParam(name = "date", required = true, defaultValue = "none") Date date,
            Map<String, Object> model)
    {
        Task task = taskService.findByTargetAndAuthor(target, user.getUsername());
        if (task != null){
            model.put("exist", "Such target already exists");
            return "addtask";
        }
        task = new Task(target, description, date, user);
        taskService.add(task);
        return "redirect:/main";
    }

    @GetMapping("/edittask/{id}")
    public String editPage(
            @PathVariable("id") Long id,
            Map<String, Object> model)
    {
        model.put("id", id);
        return "/edittask";
    }

    @PostMapping("/edittask")
    public String editTask(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "target", required = true, defaultValue = "none") String target,
            @RequestParam(name = "description", required = true, defaultValue = "none") String description,
            @RequestParam(name = "date", required = true, defaultValue = "none") Date date)
    {
        Task task = taskService.findById(id);
        task.setTarget(target);
        task.setDescription(description);
        task.setDate(date);
        taskService.update(task);
        return "redirect:/main";
    }

    @GetMapping("/deletetask/{id}")
    public ModelAndView delete(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "id") Long id){
        Task task = taskService.findById(id);
        taskService.delete(task);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        Iterable<Task> tasks = taskService.findByAuthorName(user.getUsername());
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }
}
