package com.project.scid.repo;

import com.project.scid.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
   Optional<Department> findById(Long id);
   List<Department> findByBossDepartment(Department bossDep);
}
