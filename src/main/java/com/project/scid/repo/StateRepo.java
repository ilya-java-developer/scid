package com.project.scid.repo;

import com.project.scid.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository<State, Long> {
}
