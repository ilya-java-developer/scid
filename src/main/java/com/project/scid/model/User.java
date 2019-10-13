package com.project.scid.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="usr")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private  Department department;

    @ManyToOne
    @JoinColumn(name = "boss_id")
    private User boss;

    @OneToMany
    @JoinColumn(name="usr_id")
    private List<Task> taskList;
}
