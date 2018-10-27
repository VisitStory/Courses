package net.tecgurus.courses.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String secondLastName;

    private String phone;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

}
