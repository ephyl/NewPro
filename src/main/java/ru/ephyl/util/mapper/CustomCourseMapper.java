package ru.ephyl.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.ephyl.dto.CourseDtoWithStudentNames;
import ru.ephyl.model.Course;
import ru.ephyl.model.Student;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CustomCourseMapper {
    CustomCourseMapper INSTANCE = Mappers.getMapper(CustomCourseMapper.class);

    @Mapping(source = "students", target = "studentNames", qualifiedByName = "studentToNames")
    public CourseDtoWithStudentNames customCourseMapper(Course course);

    @Named("studentToNames")
    public static List<String> studentToNames(List <Student> students ) {
        return students.stream().map(Student::getName).collect(Collectors.toList());
    }
}
