package com.project.scid.controller;


import com.project.scid.model.Department;
import com.project.scid.model.Task;
import com.project.scid.model.User;
import com.project.scid.repo.DepartmentRepo;
import com.project.scid.repo.StateRepo;
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
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private StateRepo stateRepo;

    @GetMapping("/tasks/state/{id}")
    Collection<Task> tasksByState( @PathVariable Long id) {
        return taskRepo.findByState(stateRepo.findById(id).get());
    }

    @GetMapping("/tasks")
    Collection<Task> tasks() { return taskRepo.findAll();}

    @PutMapping("/task")
    public Task updateTask(@Valid @RequestBody Task task) throws URISyntaxException {
        Task result = taskRepo.save(task);
        return  result;
    }



    @GetMapping("/task/{id}")
    ResponseEntity<?> getTask(@PathVariable Long id) {
        Optional<Task> task = taskRepo.findById(id);
        return task.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/user/{id}/tasks")
    Collection<Task> getUserTasks(@PathVariable Long id) {
        return userRepo.findById(id).get().getTaskList();
    }

    @GetMapping("/department/{id}/tasks")
    Collection<Task> getDepartmentTasks(@PathVariable Long id) {
        return departmentRepo.findById(id).get().getTaskList();
    }

    @PostMapping("/user/{id}/task")
    ResponseEntity<?> createUserTask(@Valid @RequestBody Task task, @PathVariable Long id) throws URISyntaxException {
        log.info("Request to create user task: {}", task);

        Task result = taskRepo.save(task);
        User user = userRepo.findById(id).get();
        user.getTaskList().add(result);
         userRepo.save(user);
        return ResponseEntity.created(new URI("/api/task/" + result.getId()))
                .body(result);
    }

    @PostMapping("/department/{id}/task")
    public ResponseEntity<?> createDepartamentTask(@Valid @RequestBody Task task, @PathVariable Long id) throws URISyntaxException {
        log.info("Request to create department task: {}", task);
        Task result = taskRepo.save(task);
        Department department = departmentRepo.findById(id).get();
        department.getTaskList().add(result);
        departmentRepo.save(department);
        return ResponseEntity.created(new URI("/api/task/" + result.getId()))
                .body(result);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        log.info("Request to delete task: {}", id);
        taskRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/task/{id}/state/{idState}")
    public ResponseEntity<?> updateTaskState(@PathVariable Long idState, @PathVariable Long id) throws URISyntaxException {
        Task task = taskRepo.findById(id).get();
                  task.setState(stateRepo.findById(idState).get());
          Task result = taskRepo.save(task);

        return ResponseEntity.created(new URI("/api/task/" + result.getId()))
                .body(result);
    }







}
