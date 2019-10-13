//package com.project.scid.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@Table
//public class DepartmentTaskContainer {
//    @Id
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "dep_id")
//    private Department department;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "task_id")
//    private Task task;
//
//}
