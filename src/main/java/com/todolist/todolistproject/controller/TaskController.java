package com.todolist.todolistproject.controller;

import com.todolist.todolistproject.mapper.TaskStatusUpdateRequest;
import com.todolist.todolistproject.mapper.TaskTitleUpdateRequest;
import com.todolist.todolistproject.model.Task;
import com.todolist.todolistproject.model.User;
import com.todolist.todolistproject.service.TaskService;
import com.todolist.todolistproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping("/tasks")
    public String getTasks(Model model, Principal principal) {
        User user = userService.findByName(principal.getName());
        List<Task> tasks = taskService.getAllTasksByUser(user);
        model.addAttribute("tasks", tasks);

        Task task = new Task();
        model.addAttribute("task", task);

        return "tasks/all-tasks";
    }

    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute("task") Task task, Principal principal) {
        User user = userService.findByName(principal.getName());
        task.setUser(user);
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @PostMapping("/update-task")
    public ResponseEntity<String> updateTaskTitle(@RequestBody TaskTitleUpdateRequest request) {
        Task task = taskService.getTask(request.getId());
        task.setTitle(request.getTitle());
        taskService.saveTask(task);

        return ResponseEntity.ok("Title was changed!");
    }

    @PostMapping("/update-status")
    public ResponseEntity<String> updateTaskStatus(@RequestBody TaskStatusUpdateRequest request) {
        Task task = taskService.getTask(request.getId());
        task.setCompleted(request.isCompleted());
        taskService.saveTask(task);

        return ResponseEntity.ok("Title was changed!");
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam("taskId") long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}