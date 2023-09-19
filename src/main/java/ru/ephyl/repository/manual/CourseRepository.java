package ru.ephyl.repository.manual;

import org.springframework.stereotype.Repository;
import ru.ephyl.model.Course;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository implements CrudManualRepo<Course>{

    @Override
    public List<Course> getAll() {
        return null;
    }

    @Override
    public Optional<Course> findById(int id) {
        return Optional.empty();
    }

    @Override
    public long addNew(Course object) {
        return 12;
    }

    @Override
    public void delete(int id) {
        System.out.println("DELETE");
    }

    @Override
    public boolean update(Course object) {
        return false;
    }
}
