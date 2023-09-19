package ru.ephyl.repository;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.ephyl.config.AppConfig;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {AppConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class DbTest {
    @Autowired
    private StudentCrudRepository studentCrudRepository;

    @Test
    public void someTest() {
        System.out.println(studentCrudRepository.findAllStudentsUnderThirty());
        Assertions.assertEquals(1, studentCrudRepository.findAllStudentsUnderThirty().size());
    }
}
