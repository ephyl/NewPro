package ru.ephyl.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.ephyl.config.JPAConfig;
import ru.ephyl.model.Course;
import ru.ephyl.model.Teacher;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JPAConfig.class})
@WebAppConfiguration
@Testcontainers
class CourseCrudRepositoryTest {
    CourseCrudRepository repository;
    TeacherCrudRepository teacherCrudRepository;

    @Autowired
    public CourseCrudRepositoryTest(CourseCrudRepository repository, TeacherCrudRepository teacherCrudRepository) {
        this.repository = repository;
        this.teacherCrudRepository = teacherCrudRepository;
    }

    @Container
    private final static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    ).withInitScript("init_postgresql.sql")
            .withUsername("test")
            .withPassword("test");

    @BeforeEach
    public void setup() throws Exception {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("hibernate.connection.url", postgreSQLContainer::getJdbcUrl);
        registry.add("hibernate.connection.username", postgreSQLContainer::getUsername);
        registry.add("hibernate.connection.password", postgreSQLContainer::getPassword);
    }

    @Test
    void getAllCoursesWithStudents() {
        List<Course> courseList = repository.getAllCoursesWithStudents();
        int sum = courseList.stream().mapToInt(c -> c.getStudents().size()).sum();
        Assertions.assertNotNull(courseList);
        assertEquals(4, courseList.size());
        assertEquals(6, sum);
    }

    @Test
    void findCourseById() {
        Course course = repository.findById(1).get();
        assertEquals("Java", course.getName());
        assertThrows(NoSuchElementException.class, () -> repository.findById(100).get());
    }

    @Test
    @Transactional
    void saveNewCourse() {

        Teacher teacher = teacherCrudRepository.findById(1).get();
        Course course = new Course("Joda Course", teacher);

        Course savedCourse = repository.save(course);

        assertEquals("Joda Course", savedCourse.getName());
        assertEquals(
                5, repository.getAllCoursesWithStudents().size());
    }
    @Test
    @Transactional
    void updateOldCourse() {

        Teacher teacher = teacherCrudRepository.findById(1).get();
        Course course = repository.findById(1).get();
        course.setName("Fantastic Name");

        Course updatedCourse = repository.save(course);

        assertEquals("Fantastic Name", updatedCourse.getName());
        assertEquals(

                4
                , repository.getAllCoursesWithStudents().size());
    }

    @Test
    @Transactional
    void deleteCourse(){
        repository.deleteById(3);
        assertEquals(3, repository.getAllCoursesWithStudents().size());

    }

}