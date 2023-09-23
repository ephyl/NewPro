package ru.ephyl.repository;

import org.junit.jupiter.api.AfterAll;
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
import ru.ephyl.config.AppConfig;
import ru.ephyl.config.JPAConfig;
import ru.ephyl.model.Gender;
import ru.ephyl.model.Student;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JPAConfig.class})
@WebAppConfiguration
@Testcontainers
class StudentCrudRepositoryTest {

    private final StudentCrudRepository studentCrudRepository;

    @Autowired
    public StudentCrudRepositoryTest(StudentCrudRepository studentCrudRepository) {
        this.studentCrudRepository = studentCrudRepository;
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
    void findAllStudentsUnderThirty() {
        assertEquals(1, studentCrudRepository.findAllStudentsUnderThirtyOne().size());
    }

    @Test
    void findAllStudents() {
        assertEquals(3, studentCrudRepository.findAll().size());
    }

    @Test
    void findStudentById() {
        assertEquals("Egor Filippov", studentCrudRepository.findById(1).get().getName());
    }

    @Test
    @Transactional
    void saveNewStudent() {
        Student student = new Student("New Student", 30, Gender.MALE);
        Student savedStudent = studentCrudRepository.save(student);
        assertEquals(4, studentCrudRepository.findAll().size());
        assertEquals("New Student", savedStudent.getName());
    }

    @Test
    @Transactional
    void updateOldStudent() {
        int size = studentCrudRepository.findAll().size();
        Student student = studentCrudRepository.findById(3).get();
        student.setAge(99);
        Student savedStudent = studentCrudRepository.save(student);
        assertEquals(size, studentCrudRepository.findAll().size());
        assertEquals(99 , savedStudent.getAge());
    }
    @Test
    @Transactional
    void deleteStudent() {
        int size = studentCrudRepository.findAll().size();
        studentCrudRepository.deleteById(3);
        assertEquals(size-1, studentCrudRepository.findAll().size());
        assertThrows(NoSuchElementException.class, () -> studentCrudRepository.findById(3).get());
    }

}