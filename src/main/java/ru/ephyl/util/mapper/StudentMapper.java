package ru.ephyl.util.mapper;

import org.mapstruct.Mapper;
import ru.ephyl.dto.StudentDto;
import ru.ephyl.model.Student;

@Mapper
public interface StudentMapper {
StudentDto sourceToDestination(Student student);
Student destinationToSource(StudentDto studentDto);
}
