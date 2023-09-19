package ru.ephyl.controller;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ephyl.dto.TeacherDto;
import ru.ephyl.util.mapper.TeacherMapper;
import ru.ephyl.model.Teacher;
import ru.ephyl.service.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/getAll")
    public List<TeacherDto> getTeachers() {
        return teacherService.findAll().stream().map(x -> convertToTeacherDto(x)).collect(Collectors.toList());
    }

    @GetMapping("/id")
    public TeacherDto getOneTeacher(@RequestParam String id) {
        return convertToTeacherDto(teacherService.findById(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody TeacherDto teacherDto) {
        teacherService.save(covertToTeacher(teacherDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestParam String id) {
        teacherService.delete(Integer.parseInt(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody TeacherDto teacherDto, @RequestParam String id) {
        Teacher teacherToBeUpdated = covertToTeacher(teacherDto);
        teacherService.update(teacherToBeUpdated, Integer.parseInt(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Teacher covertToTeacher(TeacherDto teacherDto) {
        TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
        return teacherMapper.destinationToSource(teacherDto);
    }

    private TeacherDto convertToTeacherDto(Teacher teacher) {
        TeacherMapper teacherMapper = Mappers.getMapper(TeacherMapper.class);
        return teacherMapper.sourceToDestination(teacher);
    }
}
