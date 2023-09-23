package ru.ephyl.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ephyl.dto.CourseDto;
import ru.ephyl.dto.CourseDtoWithStudentNames;
import ru.ephyl.model.Course;
import ru.ephyl.service.CourseService;
import ru.ephyl.util.mapper.CourseMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class CourseControllerTest {
    private final int ID = 1;
    @Mock
    CourseService courseService;
    @InjectMocks
    CourseController courseController;

    @Test
    void getCourses() {
        List<Course> courseList = Mockito.mock(List.class);
        when(courseService.findAll()).thenReturn(courseList);
        List<CourseDtoWithStudentNames> exp = courseController.getCourses();
        verify(courseService).findAll();
    }

    @Test
    void getOneCourseById() {
        Course course = mock(Course.class);
        when(courseService.findById(ID)).thenReturn(course);
        CourseDtoWithStudentNames courseDto = courseController.getOneCourseById(String.valueOf(ID));
        assertNotNull(courseDto);
        verify(courseService).findById(ID);
    }

    @Test
    void create() {
        CourseDto courseDto =  Mockito.mock(CourseDto.class);
        courseController.create(courseDto);
        verify(courseService).save(any());
    }

    @Test
    void delete() {
        courseController.delete(String.valueOf(ID));
        verify(courseService).delete(ID);
    }

    @Test
    void update() {
        CourseDto courseDto = mock(CourseDto.class);
        courseController.update(courseDto, String.valueOf(ID));
        CourseMapper mapper =  Mappers.getMapper(CourseMapper.class);
        verify(courseService).update(mapper.destinationToSource(courseDto), ID);
    }
}