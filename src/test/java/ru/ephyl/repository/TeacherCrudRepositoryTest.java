package ru.ephyl.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.ephyl.config.AppConfig;
import ru.ephyl.model.Teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@Testcontainers
class TeacherCrudRepositoryTest {


    @Autowired
    TeacherCrudRepository teacherCrudRepository;

    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    @BeforeEach
    public void setup() throws Exception {

        postgreSQLContainer.start();
        DriverManagerDataSource dataSourceTestContainer = new DriverManagerDataSource();
        dataSourceTestContainer.setUrl(postgreSQLContainer.getJdbcUrl());
        dataSourceTestContainer.setUsername(postgreSQLContainer.getUsername());
        dataSourceTestContainer.setPassword(postgreSQLContainer.getPassword());
        localContainerEntityManagerFactoryBean.setDataSource(dataSourceTestContainer);
    }

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    ).withInitScript("init_postgresql.sql");

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }


    @Test
    public void UniTest() {
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