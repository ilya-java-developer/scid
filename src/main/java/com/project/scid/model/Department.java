package com.project.scid.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_id")
    private Department bossDepartment;

    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Task> taskList;

}
