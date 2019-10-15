package com.project.scid.repo;

import com.project.scid.model.Task;
import com.project.scid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepo  extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
