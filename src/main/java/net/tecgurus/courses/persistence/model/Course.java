package net.tecgurus.courses.persistence.model;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private Timestamp startDate;
    private Timestamp endDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    // course ( id, name )
    // student ( id, name)
    // course_student ( course_id, student_id )

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(
                    name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id")
    )
    private List<Student> students;

}
