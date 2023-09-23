package ru.ephyl.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ephyl.model.Student;
import ru.ephyl.repository.StudentCrudRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class StudentServiceTest {
    private static int ID = 1;
    @Mock
    private StudentCrudRepository repo;
    @InjectMocks
    private StudentService studentService;
    @Mock
    private Student student;

    @Test
    void findAll() {
        when(repo.findAll()).thenReturn(List.of(student));
        studentService.findAll();
        verify(repo).findAll();
    }
    @Test
    void findAllunder31() {
        when(repo.findAllStudentsUnderThirtyOne()).thenReturn(List.of(student));
        studentService.findYoungUnder31();
        verify(repo).findAllStudentsUnderThirtyOne();
    }

    @Test
    void findById() {
        int id = 1;
        when(repo.findById(id)).thenReturn(Optional.of(student));
        studentService.findById(id);
        verify(repo).findById(id);
    }

    @Test
    void addNew() {
        studentService.save(student);
        verify(repo).save(student);
    }

    @Test
    void delete() {
        when(student.getId()).thenReturn(ID);
        studentService.delete(student);
        verify(repo).deleteById(ID);
    }

    @Test
    void update() {
        studentService.update(student);
        verify(repo).save(student);
    }

}