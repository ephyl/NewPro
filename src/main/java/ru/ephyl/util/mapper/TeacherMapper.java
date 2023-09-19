package ru.ephyl.util.mapper;

import org.mapstruct.Mapper;
import ru.ephyl.dto.TeacherDto;
import ru.ephyl.model.Teacher;


@Mapper
public interface TeacherMapper {
    TeacherDto sourceToDestination(Teacher teacher);
    Teacher destinationToSource(TeacherDto teacherDto);
}
