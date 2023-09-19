package ru.ephyl.util.mapper;

import org.mapstruct.Mapper;
import ru.ephyl.dto.CourseDto;
import ru.ephyl.model.Course;

@Mapper
public interface CourseMapper {
    CourseDto sourceToDestination(Course course);
    Course destinationToSource(CourseDto courseDtoDto);
}
