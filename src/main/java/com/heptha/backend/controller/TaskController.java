package com.heptha.backend.controller;

import com.heptha.backend.dto.ApiResponse;
import com.heptha.backend.dto.TaskRequest;
import com.heptha.backend.entity.Task;
import com.heptha.backend.service.TaskService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Task>> createTask(
            @RequestBody TaskRequest request) {

        Task task = taskService.createTask(request);

        ApiResponse<Task> response =
                ApiResponse.<Task>builder()
                        .success(true)
                        .message("Task created successfully")
                        .statusCode(201)
                        .data(task)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<Task>>> getAllTasks() {

        List<Task> tasks = taskService.getAllTasks();

        ApiResponse<List<Task>> response =
                ApiResponse.<List<Task>>builder()
                        .success(true)
                        .message("Tasks fetched successfully")
                        .statusCode(200)
                        .data(tasks)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> getTaskById(
            @PathVariable Long id) {

        Task task = taskService.getTaskById(id);

        ApiResponse<Task> response =
                ApiResponse.<Task>builder()
                        .success(true)
                        .message("Task fetched successfully")
                        .statusCode(200)
                        .data(task)
                        .build();

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/paginated")
    public ResponseEntity<ApiResponse<Page<Task>>> getPaginatedTasks(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size,
                        @RequestParam(defaultValue = "id") String sortBy) {

        Page<Task> tasks =taskService.getPaginatedTasks(page,size,sortBy);

        ApiResponse<Page<Task>> response =ApiResponse.<Page<Task>>builder().success(true)
                        .message("Tasks fetched successfully").statusCode(200).data(tasks).build();

        return ResponseEntity.ok(response);
    }
}