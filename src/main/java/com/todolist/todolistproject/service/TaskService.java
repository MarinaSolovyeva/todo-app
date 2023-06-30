package com.todolist.todolistproject.service;

import com.todolist.todolistproject.model.Task;
import com.todolist.todolistproject.model.User;
import com.todolist.todolistproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Transactional
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void update(long id, Task updatedTask) {
        updatedTask.setId(id);
        taskRepository.save(updatedTask);
    }

    @Transactional
    public Task getTask(long id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        return foundTask.orElse(null);
    }

    @Transactional
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public void addTask(Task task, User user) {
        List <Task> tasks = user.getTasks();
        tasks.add(task);
    }

    @Transactional
    public void deleteTask(Task task, String username) {
        User user = userService.findByName(username);
        List <Task> tasks = user.getTasks();
        tasks.remove(task);
    }

    @Transactional
    public List<Task> getAllTasksByUser(User user) {
        List <Task> taskList = taskRepository.findAllTasksByUser(user.getId());
        return taskList;
    }

}