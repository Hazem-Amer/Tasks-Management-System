package org.example.tasksmanagementsystem.controller;


import org.example.tasksmanagementsystem.dtos.tasks.TaskRequestDto;
import org.example.tasksmanagementsystem.dtos.tasks.TaskResponse;
import org.example.tasksmanagementsystem.dtos.tasks.TaskUpdateRequestDto;
import org.example.tasksmanagementsystem.model.ResponseModel;
import org.example.tasksmanagementsystem.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) { this.taskService = taskService; }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskRequestDto req) {
        return ResponseEntity.ok().body(
                ResponseModel.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Task created successfully")
                        .data(Map.of("Task", taskService.create(req)))
                        .build());

    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok().body(
                ResponseModel.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Tasks retrieved successfully")
                        .data(Map.of("Tasks", taskService.listForCurrentUser()))
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TaskUpdateRequestDto req) {
        return ResponseEntity.ok().body(
                ResponseModel.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Task updated successfully")
                        .data(Map.of("Tasks", taskService.update(id, req)))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok().body(
                ResponseModel.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Task deleted successfully")
                        .build());
    }
}
