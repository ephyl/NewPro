package ru.ephyl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ephyl.model.Course;
import ru.ephyl.model.Student;
import ru.ephyl.repository.CourseCrudRepository;
import ru.ephyl.repository.StudentCrudRepository;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class CourseManagerServiceTest {
    private static int ID = 1;
    @Mock
    private CourseCrudRepository courseRepo;
    @Mock
    private StudentCrudRepository studentRepo;
    @InjectMocks
    private CourseManagerService courseManagerService;
    @Mock
    private Course course;
    @Mock
    private Student student;

    @Test
    void assign() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(ID, integerList);
        when(courseRepo.findById(ID)).thenReturn(Optional.of(course));

        when(studentRepo.findById(ID)).thenReturn(Optional.of(student));
        courseManagerService.assign(map);
        verify(courseRepo).findById(ID);
        verify(studentRepo).findById(ID);
    }

    @Test
    void delete() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(ID, integerList);
        when(courseRepo.findById(ID)).thenReturn(Optional.of(course));
        when(courseRepo.save(any())).thenReturn(course);

        courseManagerService.delete(map);

        verify(courseRepo).findById(ID);
        verify(courseRepo).save(any());


    }
}