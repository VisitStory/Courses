package net.tecgurus.courses.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.tecgurus.courses.dto.course.CourseCreateDTO;
import net.tecgurus.courses.dto.course.CourseOutDTO;
import net.tecgurus.courses.dto.course.CourseUpdateDTO;
import net.tecgurus.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "coursesApi", description = "Operations for courses")
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    @ApiOperation(value = "Retrieve all courses", response = CourseOutDTO.class, responseContainer = "List")
    public List<CourseOutDTO> findAll() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{courseId}")
    @ApiOperation(value = "Retrieve one course according to one id", response = CourseOutDTO.class)
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
    @ApiOperation(value = "Creates a new courseCourseCreateDTO", response = CourseOutDTO.class)
    public ResponseEntity create(@Valid @RequestBody CourseCreateDTO courseCreate, BindingResult br) {

        if (br.hasErrors())
            return ResponseEntity.badRequest().body(br.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage()));

        return ResponseEntity.ok(courseService.createCourse(courseCreate));
    }

    @PutMapping("/{courseId}")
    @ApiOperation(value = "Updates a course", response = CourseOutDTO.class)
    public ResponseEntity update(@PathVariable("courseId") Long courseId,
                                 @RequestBody CourseUpdateDTO courseUpdate) {
        try {
            return ResponseEntity.ok(courseService.updateCourse(courseId, courseUpdate));
        } catch (Exception ex) {
            // Builder
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{courseId}")
    @ApiOperation(value = "Delete one course", response = CourseOutDTO.class)
    public ResponseEntity delete(@PathVariable("courseId") Long courseId) throws Exception {
        return ResponseEntity.ok(courseService.deleteCourse(courseId));
    }

}
