package ru.ephyl.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ephyl.model.Teacher;
import ru.ephyl.repository.TeacherCrudRepository;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith({MockitoExtension.class})
class TeacherServiceTest {
    private static final int ID = 1;
    @Mock
    private TeacherCrudRepository repo;
    @InjectMocks
    private TeacherService teacherService;
    @Mock
    private Teacher teacher;

    @Test
    void findById() {

        when(repo.findById(ID)).thenReturn(Optional.of(teacher));
        teacherService.findById(ID);
        verify(repo).findById(ID);
    }

    @Test
    void update() {
        teacherService.update(teacher, ID);
        verify(repo).save(teacher);
    }

    @Test
    void delete() {
        teacherService.delete(ID);
        verify(repo).deleteById(ID);
    }

    @Test
    void addNew() {
        teacherService.save(teacher);
        verify(repo).save(teacher);
    }

}