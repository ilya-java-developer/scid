package com.project.scid.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String body;

    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;
//    private Department departmentOwner;
//    private User userOwner;

}
