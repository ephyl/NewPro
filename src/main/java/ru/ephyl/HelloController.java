package ru.ephyl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping
    public String getAll(){
        return  "123";
    }
}
