package com.project.scid.repo;

import com.project.scid.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo  extends JpaRepository<Task, Long> {

}
