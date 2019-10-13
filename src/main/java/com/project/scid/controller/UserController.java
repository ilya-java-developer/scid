package com.project.scid.controller;


import com.project.scid.model.User;
import com.project.scid.repo.DepartmentRepo;
import com.project.scid.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    public DepartmentRepo departmentRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    Collection<User> users() { return userRepo.findAll();}

    @GetMapping("/user/{id}")
    ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{id}/subusers")
    Collection<User> getSubUsers(@PathVariable Long id) {
        return userRepo.findByBoss(userRepo.findById(id).get());
    }

    @PutMapping("/user/{id}")
    ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        log.info("Request to update department: {}", user);
        User result = userRepo.save(user);
        return ResponseEntity.ok().body(result);
    }

}
