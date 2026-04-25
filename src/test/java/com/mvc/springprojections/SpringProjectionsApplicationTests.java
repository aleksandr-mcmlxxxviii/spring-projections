package com.mvc.springprojections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SpringProjectionsApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void contextLoads() {
        System.out.println("Context loaded!");
    }
}
