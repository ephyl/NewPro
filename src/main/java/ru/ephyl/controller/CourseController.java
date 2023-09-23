package ru.ephyl.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ephyl.dto.CourseDto;
import ru.ephyl.dto.CourseDtoWithStudentNames;
import ru.ephyl.exception.CourseNotFoundException;
import ru.ephyl.model.Course;
import ru.ephyl.service.CourseService;
import ru.ephyl.util.mapper.CourseMapper;
import ru.ephyl.util.mapper.CustomCourseMapper;

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

    @GetMapping("/get-all")
    public List<CourseDtoWithStudentNames> getCourses() {
        return courseService.findAll().stream().map(this::convertToCourseDtoWithStudentNames).collect(Collectors.toList());
    }

    @GetMapping()
    public CourseDtoWithStudentNames getOneCourseById(@RequestParam String id) {
        return convertToCourseDtoWithStudentNames(courseService.findById(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody CourseDto courseDto) {
        courseService.save(convertToCourse(courseDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestParam String id) {
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
    private CourseDtoWithStudentNames convertToCourseDtoWithStudentNames(Course course) {
        CustomCourseMapper mapper = Mappers.getMapper(CustomCourseMapper.class);
        return mapper.customCourseMapper(course);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(CourseNotFoundException exception){
        return new ResponseEntity<>("Course not found" , HttpStatus.BAD_REQUEST);
    }

}
