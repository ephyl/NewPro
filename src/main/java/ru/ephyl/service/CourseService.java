package ru.ephyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ephyl.exception.CourseNotFoundException;
import ru.ephyl.model.Course;
import ru.ephyl.model.Student;
import ru.ephyl.model.Teacher;
import ru.ephyl.repository.CourseCrudRepository;

import java.util.List;

@Service
public class CourseService {

    private CourseCrudRepository repository;

    @Autowired
    public CourseService(CourseCrudRepository repository) {
        this.repository = repository;
    }

    public List<Course> findAll(){
        return repository.findAll();
    }
    public Course findById(int id) throws CourseNotFoundException {
        return   repository.findById(id).orElseThrow( CourseNotFoundException::new);
    }

    public void save(Course course){
        repository.save(course);
    }
    public void delete(int id){
        repository.deleteById(id);
    }

    public void update(Course course, int id){
        course.setId(id);
        repository.save(course);
    }
}
