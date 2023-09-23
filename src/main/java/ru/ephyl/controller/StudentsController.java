package ru.ephyl.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ephyl.dto.StudentDto;
import ru.ephyl.exception.StudentNotFoundException;
import ru.ephyl.model.Student;
import ru.ephyl.service.StudentService;
import ru.ephyl.util.mapper.StudentMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentsController {
    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.findAll().stream().map(this::convertToStudentDto).collect(Collectors.toList());
    }
    @GetMapping("/id")
    public StudentDto getOneCourseById(@RequestParam String id) {
        return convertToStudentDto(studentService.findById(Integer.parseInt(id)));
    }
    @GetMapping("/young")
    public List<StudentDto> getUnder31() {
        return studentService.findYoungUnder31().stream().map(this::convertToStudentDto).collect(Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody StudentDto studentDto) {
        studentService.save(convertToStudent(studentDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody StudentDto studentDto) {
        studentService.delete(convertToStudent(studentDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping
    public ResponseEntity<HttpStatus> update(@RequestBody StudentDto studentDto) {
        studentService.update(convertToStudent(studentDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Student convertToStudent(StudentDto studentDto) {
        StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
        return mapper.destinationToSource(studentDto);
    }

    private StudentDto convertToStudentDto(Student student) {
        StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
        return mapper.sourceToDestination(student);
    }
    @ExceptionHandler
    private ResponseEntity<String> handleException(StudentNotFoundException exception){
        return new ResponseEntity<>(exception.toString() , HttpStatus.BAD_REQUEST);
    }

}
