package org.example.tasksmanagementsystem.controller;


import org.example.tasksmanagementsystem.dtos.tasks.TaskRequestDto;
import org.example.tasksmanagementsystem.dtos.tasks.TaskResponse;
import org.example.tasksmanagementsystem.dtos.tasks.TaskUpdateRequestDto;
import org.example.tasksmanagementsystem.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) { this.taskService = taskService; }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequestDto req) {
        TaskResponse t = taskService.create(req);
        return ResponseEntity.status(201).body(t);
    }

    @GetMapping
    public List<TaskResponse> list() {
        return taskService.listForCurrentUser();
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @RequestBody TaskUpdateRequestDto req) {
        return taskService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
