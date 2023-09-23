package ru.ephyl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ephyl.exception.TeacherNotFoundException;
import ru.ephyl.model.Teacher;
import ru.ephyl.repository.TeacherCrudRepository;

import java.util.List;

@Service
public class TeacherService {
    private TeacherCrudRepository repository;

    @Autowired
    public TeacherService(TeacherCrudRepository teacherCrudRepository) {
        this.repository = teacherCrudRepository;
    }

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    public Teacher findById(int id) throws TeacherNotFoundException {
        return repository.findById(id).orElseThrow(TeacherNotFoundException::new);
    }

    public void save(Teacher teacher) {
        repository.save(teacher);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void update(Teacher teacher, int id) {
        teacher.setId(id);
        repository.save(teacher);
    }

}
