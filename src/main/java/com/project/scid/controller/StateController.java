package com.project.scid.controller;


import com.project.scid.model.Department;
import com.project.scid.model.State;
import com.project.scid.repo.StateRepo;
import com.project.scid.repo.TaskRepo;
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
public class StateController {
    @Autowired
    private StateRepo stateRepo;

    @GetMapping("/state/{id}")
    ResponseEntity<?> stateById(@PathVariable Long id) {
        Optional<State> state = stateRepo.findById(id);
        return state.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/states")
    Collection<State> states() { return stateRepo.findAll();}

    @PostMapping("/states")
    ResponseEntity<?> addState(@Valid @RequestBody State state) throws URISyntaxException  {
        State result = stateRepo.save(state);
        return ResponseEntity.created(new URI("/api/state/" + result.getId()))
                .body(result);
    }

}


