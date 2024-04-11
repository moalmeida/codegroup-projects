package br.com.codegroup.projects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Test
    public void contextLoads() {
        Application.main(new String[]{});
    }

}