package com.heptha.backend.service.impl;

import com.heptha.backend.dto.TaskRequest;
import com.heptha.backend.entity.Task;
import com.heptha.backend.entity.User;
import com.heptha.backend.repository.TaskRepository;
import com.heptha.backend.repository.UserRepository;
import com.heptha.backend.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public Task createTask(TaskRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .user(user)
                .build();

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {

        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Task updateTask(Long id, TaskRequest request) {

        Task task = getTaskById(id);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {

        taskRepository.deleteById(id);
    }
    
    @Override
    public Page<Task> getPaginatedTasks(int page,int size,String sortBy) {

        Pageable pageable =PageRequest.of(page,size,Sort.by(sortBy).descending());

        return taskRepository.findAll(pageable);
    }
    
}