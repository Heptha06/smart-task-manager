package com.heptha.backend.controller;

import com.heptha.backend.dto.TaskRequest;
import com.heptha.backend.entity.Task;
import com.heptha.backend.service.TaskService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody TaskRequest request) {

        return taskService.createTask(request);
    }

    @GetMapping
    public List<Task> getAllTasks() {

        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {

        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequest request) {

        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return "Task deleted successfully";
    }
}