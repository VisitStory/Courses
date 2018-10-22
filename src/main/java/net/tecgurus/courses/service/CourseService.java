package net.tecgurus.courses.service;

import net.tecgurus.courses.dto.course.CourseCreateDTO;
import net.tecgurus.courses.dto.course.CourseOutDTO;
import net.tecgurus.courses.persistence.model.Course;
import net.tecgurus.courses.persistence.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseOutDTO> findAllCourses() {

        /* Primera forma
        List<Course> courses = courseRepository.findAll();
        List<CourseOutDTO> coursesDto = new ArrayList<>();
        for (Course course : courses) {
            CourseOutDTO courseDTO = new CourseOutDTO();
            courseDTO.setId(course.getId());
            courseDTO.setName(course.getName());
            courseDTO.setDescription(course.getDescription());

            coursesDto.add(courseDTO);
        }
        return coursesDto;
        */

        /* Segunda forma
        List<Course> courses = courseRepository.findAll();
        List<CourseOutDTO> coursesDto = new ArrayList<>();
        for (Course course : courses) {
            CourseOutDTO courseDTO = modelMapper.map(course, CourseOutDTO.class);
            coursesDto.add(courseDTO);
        }
        return coursesDto;
        */

        // Tercera forma
        return courseRepository.findAll().stream()
                .map(course -> modelMapper.map(course, CourseOutDTO.class))
                .collect(Collectors.toList());
    }

    public CourseOutDTO findCourseById(Long courseId) throws Exception {

        /* Primera forma
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        boolean present = optionalCourse.isPresent();

        if (present) {
            return modelMapper.map(optionalCourse.get(), CourseOutDTO.class);
        } else {
            return null;
        }
        */

        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        Course course = optionalCourse.orElseThrow(() ->
                new Exception(String.format("The course with id: %s does not exist", courseId)));

        return modelMapper.map(course, CourseOutDTO.class);
    }

    public CourseOutDTO createCourse(CourseCreateDTO courseCreate) {

        Course course = modelMapper.map(courseCreate, Course.class);

        Course savedCourse = courseRepository.save(course);

        return modelMapper.map(savedCourse, CourseOutDTO.class);
    }

}
