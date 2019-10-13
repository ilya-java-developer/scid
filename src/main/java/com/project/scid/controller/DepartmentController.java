package com.project.scid.controller;


import com.project.scid.model.Department;
import com.project.scid.model.User;
import com.project.scid.repo.DepartmentRepo;
import com.project.scid.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@Slf4j
public class DepartmentController {
    @Autowired
    public DepartmentRepo departmentRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/departments")
    Collection<Department> departments() {
        return departmentRepo.findAll();
    }

    @GetMapping("/department/{id}")
    ResponseEntity<?> getDepartment(@PathVariable Long id) {
        Optional<Department> department = departmentRepo.findById(id);
        return department.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/department/{id}/subdepartments")
    Collection<Department> getSubDepartment(@PathVariable Long id) {
        Department department = departmentRepo.findById(id).get();
        return departmentRepo.findByBossDepartment(department);
    }

    @PostMapping("/department")
    ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) throws URISyntaxException {
        log.info("Request to create department: {}", department);
        Department result = departmentRepo.save(department);
        return ResponseEntity.created(new URI("/api/department/" + result.getId()))
                .body(result);
    }

    @PutMapping("/department/{id}")
    ResponseEntity<Department> updateDepartment(@Valid @RequestBody Department department) {
        log.info("Request to update department: {}", department);
        Department result = departmentRepo.save(department);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        log.info("Request to delete department: {}", id);
        departmentRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
//-------------------------------------------------------------------


}
