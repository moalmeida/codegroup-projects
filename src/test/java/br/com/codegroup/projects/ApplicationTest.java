package br.com.codegroup.projects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTest {

    @Test
    void main() {
        Application.main(new String[]{});
    }
}