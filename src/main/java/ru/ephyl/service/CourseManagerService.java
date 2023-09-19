package ru.ephyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ephyl.exception.CourseNotFoundException;
import ru.ephyl.exception.StudentNotFoundException;
import ru.ephyl.model.Course;
import ru.ephyl.model.Student;
import ru.ephyl.repository.CourseCrudRepository;
import ru.ephyl.repository.StudentCrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CourseManagerService {
    private final CourseCrudRepository repository;
    private final StudentCrudRepository studentRepository;

    @Autowired
    public CourseManagerService(CourseCrudRepository repository, StudentCrudRepository studentCrudRepository) {
        this.repository = repository;
        this.studentRepository = studentCrudRepository;
    }

    public void assign(Map<Integer, List<Integer>> map) {
        for (Map.Entry<Integer, List<Integer>> set :
                map.entrySet()) {
            Course course = repository.findById(set.getKey()).orElseThrow(CourseNotFoundException::new);
            List<Student> studentList = course.getStudents();

            List<Integer> studentsID = set.getValue();
            for (Integer id : studentsID
            ) {
                Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
                if (!studentList.contains(student)) {
                    studentList.add(student);
                }
            }
            course.setStudents(studentList);
            repository.save(course);
        }
    }

    public void delete(Map<Integer, List<Integer>> map) {
        for (Map.Entry<Integer, List<Integer>> set :
                map.entrySet()) {
            Course course = repository.findById(set.getKey()).orElseThrow(CourseNotFoundException::new);
            List<Student> studentList = course.getStudents();

            List<Student> reducedList =  new ArrayList<>();
            List<Integer> studentsID = set.getValue();


            for (Student s: studentList
                 ) {
                if( !studentsID.contains(s.getId())){
                    reducedList.add(s);
                }
            }
            course.setStudents(reducedList);
            repository.save(course);
        }
    }
}
