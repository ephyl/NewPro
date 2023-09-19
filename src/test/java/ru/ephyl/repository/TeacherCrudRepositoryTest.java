package ru.ephyl.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.ephyl.config.AppConfig;
import ru.ephyl.model.Teacher;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
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
    ).withInitScript("init_postgresql.sql");

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
        System.out.println(connection.getSchema());



        System.out.println("1____________________________________________________________");
        System.out.println(postgreSQLContainer.getJdbcUrl());
        System.out.println("2____________________________________________________________");
        System.out.println(postgreSQLContainer.getUsername());
        System.out.println("3____________________________________________________________");
        System.out.println(postgreSQLContainer.getPassword());
        System.out.println("4____________________________________________________________");

        Teacher teacher = new Teacher("NEW");
        teacherCrudRepository.save(teacher);
        System.out.println("5____________________________________________________________");
        System.out.println(teacherCrudRepository.findById(1).get());
        System.out.println("6____________________________________________________________");
        assertEquals(3, teacherCrudRepository.findAll().size());
    }


}

//    @DynamicPropertySource
//    static void postgresqlProperties(DynamicPropertyRegistry registry) {
//        registry.add("hibernate.connection.url", postgreSQLContainer::getJdbcUrl);
//        registry.add("hibernate.connection.password", postgreSQLContainer::getPassword);
//        registry.add("hibernate.connection.username", postgreSQLContainer::getUsername);
//    }


//    @BeforeEach
//    public void setup() throws Exception {
//        postgreSQLContainer.start();
//        String jdbcUrl = String.format("jdbc:postgresql://localhost:%d/prop", postgreSQLContainer.getFirstMappedPort());
//
//        System.setProperty("hibernate.connection.url", jdbcUrl);
//        System.setProperty("hibernate.connection.username", postgreSQLContainer.getUsername());
//        System.setProperty("hibernate.connection.password", postgreSQLContainer.getPassword());
//
//    }

//
//    @Autowired
//    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;
//
//    @BeforeEach
//    public void setup() throws Exception {
//
//        postgreSQLContainer.start();
//        DriverManagerDataSource dataSourceTestContainer = new DriverManagerDataSource();
//        dataSourceTestContainer.setUrl(postgreSQLContainer.getJdbcUrl());
//        dataSourceTestContainer.setUsername(postgreSQLContainer.getUsername());
//        dataSourceTestContainer.setPassword(postgreSQLContainer.getPassword());
//        localContainerEntityManagerFactoryBean.setDataSource(dataSourceTestContainer);
//    }
