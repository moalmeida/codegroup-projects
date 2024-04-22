package br.com.codegroup.projects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void main() {
        Application.main(new String[]{});
        assertNotNull(applicationContext, "The application context should have loaded");
    }
}