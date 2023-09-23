package ru.ephyl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ephyl.exception.CourseNotFoundException;
import ru.ephyl.exception.StudentNotFoundException;
import ru.ephyl.model.Course;
import ru.ephyl.repository.CourseCrudRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class CourseServiceTest {
    private static int ID = 1;
    @Mock
    private CourseCrudRepository repo;
    @InjectMocks
    private CourseService courseService;
    @Mock
    private Course course;

    @Test
    void findAll() {
        when(repo.getAllCoursesWithStudents()).thenReturn(List.of(course));
        courseService.findAll();
        verify(repo).getAllCoursesWithStudents();
    }

    @Test
    void findById() {
        int id = 1;
        when(repo.findById(id)).thenReturn(Optional.of(course));
        courseService.findById(id);
        verify(repo).findById(id);
    }

    @Test
    void delete() {
        doNothing().when(repo).deleteById(ID);
        when(repo.findById(ID)).thenReturn(Optional.of(course));
        courseService.delete(ID);
        verify(repo).deleteById(ID);
    }

    @Test
    void deleteThrowsException() {
        when(repo.findById(any())).thenReturn(Optional.empty());
        assertThrows(CourseNotFoundException.class, () -> courseService.delete(100));
    }

    @Test
    void update() {
        when(repo.save(course)).thenReturn(course);
        when(repo.findById(ID)).thenReturn(Optional.of(course));
        courseService.update(course, ID);
        verify(repo).save(course);
    }

    @Test
    void addNew() {
        courseService.save(course);
        verify(repo).save(course);
    }
}