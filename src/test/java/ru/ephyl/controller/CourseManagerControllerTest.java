package ru.ephyl.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ephyl.service.CourseManagerService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseManagerControllerTest {
    @Mock
    CourseManagerService courseManagerService;
    @InjectMocks
    CourseManagerController controller;
    @Test
    void assign() {
        Map<Integer, List<Integer>>  mapMock =  mock(Map.class);
        doNothing().when(courseManagerService).assign(mapMock);
        controller.assign(mapMock);
        verify(courseManagerService).assign(mapMock);
    }

    @Test
    void delete() {
        Map<Integer, List<Integer>>  mapMock =  mock(Map.class);
        doNothing().when(courseManagerService).delete(mapMock);
        controller.delete(mapMock);
        verify(courseManagerService).delete(mapMock);
    }
}