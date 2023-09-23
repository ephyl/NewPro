package ru.ephyl.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ephyl.exception.CourseNotFoundException;
import ru.ephyl.model.Course;
import ru.ephyl.repository.CourseCrudRepository;

import java.util.List;

@Service
public class CourseService {
    Logger logger = LoggerFactory.getLogger(CourseService.class);

    private CourseCrudRepository repository;

    @Autowired
    public CourseService(CourseCrudRepository repository) {
        this.repository = repository;
    }

    public List<Course> findAll() {
        return repository.getAllCoursesWithStudents();
    }

    @Transactional(readOnly = true)
    public Course findById(int id) throws CourseNotFoundException {
        Course course = repository.findById(id).orElseThrow(CourseNotFoundException::new);
        Hibernate.initialize(course.getStudents());
        return course;
    }

    public void save(Course course) {
        repository.save(course);
    }

    public void delete(int id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else throw new CourseNotFoundException();
    }

    public void update(Course course, int id) {
        if (repository.findById(id).isPresent()) {
            course.setId(id);
            repository.save(course);
        } else throw new CourseNotFoundException();
    }
}
