package ru.ephyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ephyl.exception.StudentNotFoundException;
import ru.ephyl.model.Student;
import ru.ephyl.model.Teacher;
import ru.ephyl.repository.StudentCrudRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentCrudRepository repository;

    @Autowired
    public StudentService(StudentCrudRepository repository) {
        this.repository = repository;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(int id) throws StudentNotFoundException {
        return repository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public void save(Student student) {
        repository.save(student);
    }

    public void delete(Student student) {
        repository.deleteById(student.getId());
    }

    public void update(Student student) {
        repository.save(student);
    }

    public List<Student> findYoung(){
        return  repository.findAllStudentsUnderThirty();
    }
}
