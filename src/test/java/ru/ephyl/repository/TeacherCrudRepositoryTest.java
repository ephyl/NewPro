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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.ephyl.config.AppConfig;
import ru.ephyl.config.JPAConfig;
import ru.ephyl.model.Teacher;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JPAConfig.class})
@WebAppConfiguration
@Testcontainers
class TeacherCrudRepositoryTest {

    TeacherCrudRepository teacherCrudRepository;

    @Autowired
    public TeacherCrudRepositoryTest(TeacherCrudRepository teacherCrudRepository) {
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
    void UniTest() throws SQLException {
        Teacher teacher = new Teacher("NEW");
        teacherCrudRepository.save(teacher);
        assertEquals(4, teacherCrudRepository.findAll().size());
    }


}
