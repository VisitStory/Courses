package net.tecgurus.courses.persistence.repository;

import net.tecgurus.courses.persistence.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {



}
