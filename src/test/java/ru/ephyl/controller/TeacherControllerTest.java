package ru.ephyl.controller;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.Controller;
import ru.ephyl.config.AppConfig;
import ru.ephyl.dto.TeacherDto;
import ru.ephyl.model.Teacher;
import ru.ephyl.service.TeacherService;
import ru.ephyl.util.mapper.TeacherMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
class TeacherControllerTest {
    private final int ID = 1;
    @Mock
    TeacherService teacherService;
    @InjectMocks
    TeacherController teacherController;
    @Test
    void getTeachers() throws Exception {

        List<Teacher> teachers = new ArrayList<>();
        when(teacherService.findAll()).thenReturn(teachers);
        List<TeacherDto> teachers1 = teacherController.getTeachers();
        verify(teacherService).findAll();
    }

    @Test
    void getOneTeacher() {
        Teacher teacher = mock(Teacher.class);
        when(teacherService.findById(ID)).thenReturn(teacher);

        TeacherDto expectedTeacherDto = teacherController.getOneTeacher(String.valueOf(ID));
        Assertions.assertNotNull(expectedTeacherDto);
        verify(teacherService).findById(ID);
    }

    @Test
    void create() {
        TeacherDto teacherDto = mock(TeacherDto.class);
        teacherController.create(teacherDto);
        verify(teacherService).save(any());
    }

    @Test
    void delete() {
        teacherController.delete(String.valueOf(ID));
        verify(teacherService).delete(ID);
    }

    @Test
    void update() {
        TeacherDto teacherDto = mock(TeacherDto.class);
        teacherController.update(teacherDto, String.valueOf(ID));
        TeacherMapper tm = Mappers.getMapper(TeacherMapper.class);
        verify(teacherService).update(tm.destinationToSource(teacherDto), ID);
    }
}