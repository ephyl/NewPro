package ru.ephyl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ephyl.exception.CourseNotFoundException;
import ru.ephyl.exception.StudentNotFoundException;
import ru.ephyl.service.CourseManagerService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage-courses")
public class CourseManagerController {

    private final CourseManagerService courseManagerService;

    @Autowired
    public CourseManagerController(CourseManagerService courseManagerService) {
        this.courseManagerService = courseManagerService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> assign(@RequestBody Map<Integer, List<Integer>> map) {
        courseManagerService.assign(map);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody Map<Integer, List<Integer>> map) {
        courseManagerService.delete(map);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException exception){
        return new ResponseEntity<>(exception.toString() , HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException exception){
        return new ResponseEntity<>(exception.toString() , HttpStatus.BAD_REQUEST);
    }

}
