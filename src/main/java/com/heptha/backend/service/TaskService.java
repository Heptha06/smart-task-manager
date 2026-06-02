package com.heptha.backend.service;

import com.heptha.backend.dto.TaskRequest;
import com.heptha.backend.entity.Task;

import java.util.List;

import org.springframework.data.domain.Page;

public interface TaskService {

    Task createTask(TaskRequest request);

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task updateTask(Long id, TaskRequest request);

    void deleteTask(Long id);
    
    Page<Task> getPaginatedTasks(int page,int size,String sortBy);
}