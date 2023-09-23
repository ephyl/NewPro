package ru.ephyl.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ephyl.dto.StudentDto;
import ru.ephyl.model.Student;
import ru.ephyl.service.StudentService;
import ru.ephyl.util.mapper.StudentMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class StudentsControllerTest {
    private final int ID = 1;

    @Mock
    StudentService studentService;
    @InjectMocks
    StudentsController controller;
    private static  StudentMapper mapper;
    @BeforeAll
    static void init(){
        mapper = Mappers.getMapper(StudentMapper.class);
    }

    @Test
    void getAllStudents() {
        List<Student> students = Mockito.mock(ArrayList.class);
        when(studentService.findAll()).thenReturn(students);
        List<StudentDto> list =  controller.getAllStudents();
        verify(studentService).findAll();
    }

    @Test
    void getOneStudentById() {
        Student student  = mock(Student.class);
        when(studentService.findById(ID)).thenReturn(student);
        StudentDto oneCourseById = controller.getOneCourseById(String.valueOf(ID));
        verify(studentService).findById(ID);
    }

    @Test
    void getUnder31() {
        List<Student> students = Mockito.mock(ArrayList.class);
        when(studentService.findYoungUnder31()).thenReturn(students);
        List<StudentDto> under31 = controller.getUnder31();
        verify(studentService).findYoungUnder31();
    }

    @Test
    void create() {
        StudentDto studentDto  = mock(StudentDto.class);
        controller.create(studentDto);
        verify(studentService).save(any());
    }

    @Test
    void delete() {
        StudentDto studentDto  = mock(StudentDto.class);
        controller.delete(studentDto);
        verify(studentService).delete(mapper.destinationToSource(studentDto));
    }

    @Test
    void update() {
        StudentDto studentDto  = mock(StudentDto.class);
        controller.update(studentDto);
        verify(studentService).update(mapper.destinationToSource(studentDto));
    }
}