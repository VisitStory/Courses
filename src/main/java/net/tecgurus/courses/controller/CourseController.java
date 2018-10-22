package net.tecgurus.courses.controller;

import net.tecgurus.courses.dto.course.CourseCreateDTO;
import net.tecgurus.courses.dto.course.CourseOutDTO;
import net.tecgurus.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseOutDTO> findAll() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity findById(@PathVariable("courseId") Long courseId) {

        try {

            // Primer forma
            //return new ResponseEntity<>(courseService.findCourseById(courseId), HttpStatus.OK);

            // Segunda forma
            return ResponseEntity.ok(courseService.findCourseById(courseId));

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity create(@RequestBody CourseCreateDTO courseCreate) {
        return ResponseEntity.ok(courseService.createCourse(courseCreate));
    }

}
