package ru.ephyl.repository;

import jakarta.servlet.ServletContext;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.ephyl.config.AppConfig;
import ru.ephyl.model.Teacher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
class CourseControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TeacherCrudRepository teacherCrudRepository;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    @Test
    public void UniTest(){
        System.out.println("______________________________________________________________________________________");
        System.out.println(teacherCrudRepository.findAll());
        System.out.println("______________________________________________________________________________________");
//    teacherCrudRepository.save(new Teacher("new T"));

    }

    @Test
    public void getTeacherByID(){
        Assertions.assertEquals("Java Teacher" , teacherCrudRepository.findById(1).get().getName());
    }
    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(webApplicationContext.getBean("courseController"));

    }
    @Test
    void givenGreetURI_whenMockMVC_thenVerifyResponse() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/courses/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Course"))
                .andReturn();

        Assertions.assertEquals("application/json", mvcResult.getResponse().getContentType());
    }


}