package ru.ephyl.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ephyl.dto.CourseDto;
import ru.ephyl.dto.StudentDto;
import ru.ephyl.model.Course;
import ru.ephyl.model.Student;
import ru.ephyl.service.CourseService;
import ru.ephyl.util.mapper.CourseMapper;
import ru.ephyl.util.mapper.StudentMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDto> getCourses() {
        return courseService.findAll().stream().map(this::convertToCourseDTO).collect(Collectors.toList());
    }
    @GetMapping("/1")
    public CourseDto getOneCourseById() {
        return new CourseDto("New Course");
    }

    @GetMapping("/id")
    public CourseDto getOneCourseById(@RequestParam String id) {
        return convertToCourseDTO(courseService.findById(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody CourseDto courseDto) {
        courseService.save(convertToCourse(courseDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestParam  String id) {
        courseService.delete(Integer.parseInt(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody CourseDto courseDto, @RequestParam String id) {
        Course course = convertToCourse(courseDto);
        courseService.update(course, Integer.parseInt(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Course convertToCourse(CourseDto courseDto) {
        CourseMapper mapper = Mappers.getMapper(CourseMapper.class);
        return mapper.destinationToSource(courseDto);
    }

    private CourseDto convertToCourseDTO(Course course) {
        CourseMapper mapper = Mappers.getMapper(CourseMapper.class);
        CourseDto dto = mapper.sourceToDestination(course);
        List<Student> students = course.getStudents();
        StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);
        List<StudentDto> studentDtoList = students.stream().map(studentMapper::sourceToDestination).collect(Collectors.toList());
        dto.setStudentDtoList(studentDtoList);
        return dto;
    }

}
