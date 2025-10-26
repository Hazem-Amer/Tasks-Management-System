package org.example.tasksmanagementsystem.service;


import org.example.tasksmanagementsystem.dtos.tasks.TaskRequestDto;
import org.example.tasksmanagementsystem.dtos.tasks.TaskResponse;
import org.example.tasksmanagementsystem.dtos.tasks.TaskUpdateRequestDto;
import org.example.tasksmanagementsystem.model.enums.Status;
import org.example.tasksmanagementsystem.model.Task;
import org.example.tasksmanagementsystem.model.User;
import org.example.tasksmanagementsystem.repository.TaskRepository;
import org.example.tasksmanagementsystem.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    private User currentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public TaskResponse create(TaskRequestDto req) {
        User owner = currentUser();
        Task t = new Task(req.title, req.description, owner);
        Task saved = taskRepository.save(t);
        return toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> listForCurrentUser() {
        User owner = currentUser();
        return taskRepository.findByOwner(owner).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public TaskResponse update(Long id, TaskUpdateRequestDto req) {
        User owner = currentUser();
        Task t = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!t.getOwner().getId().equals(owner.getId())) {
            throw new SecurityException("Forbidden");
        }
        if (req.title != null) t.setTitle(req.title);
        if (req.description != null) t.setDescription(req.description);
        if (req.status != null) {
            t.setStatus(Status.valueOf(req.status));
        }
        return toDto(taskRepository.save(t));
    }

    @Transactional
    public void delete(Long id) {
        User owner = currentUser();
        Task t = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!t.getOwner().getId().equals(owner.getId())) {
            throw new SecurityException("Forbidden");
        }
        taskRepository.delete(t);
    }

    private TaskResponse toDto(Task t) {
        TaskResponse r = new TaskResponse();
        r.id = t.getId();
        r.title = t.getTitle();
        r.description = t.getDescription();
        r.status = t.getStatus().name();
        r.ownerId = (t.getOwner() != null) ? t.getOwner().getId() : null;
        return r;
    }
}
