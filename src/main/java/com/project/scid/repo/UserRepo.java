package com.project.scid.repo;

import com.project.scid.model.Department;
import com.project.scid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByDepartment(Department department);
    List<User> findByBoss(User user);

}
