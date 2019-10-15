package com.project.scid.controller;


import com.project.scid.model.Department;
import com.project.scid.model.Task;
import com.project.scid.model.User;
import com.project.scid.repo.TaskRepo;
import com.project.scid.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class TaskController {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/tasks")
    Collection<Task> tasks() { return taskRepo.findAll();}

    @GetMapping("/task/{id}")
    ResponseEntity<?> getTask(@PathVariable Long id) {
        Optional<Task> task = taskRepo.findById(id);
        return task.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/user/{id}/tasks")
    Collection<Task> getUserTasks(@PathVariable Long id) {
        return taskRepo.findByUser(userRepo.findById(id).get());
    }

    @PostMapping("/user/{id}/task")
    ResponseEntity<Task> createUserTask(@Valid @RequestBody Task task) throws URISyntaxException {
        log.info("Request to create task: {}", task);
        Task result = taskRepo.save(task);
        return ResponseEntity.created(new URI("/api/task/" + result.getId()))
                .body(result);
    }




}
