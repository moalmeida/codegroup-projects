package br.com.codegroup.projects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:application.properties")
public class ServletInitializerTest {

    @Test
    public void testConfigure() {
        SpringApplicationBuilder builder = mock(SpringApplicationBuilder.class);
        ServletInitializer servletInitializer = new ServletInitializer();
        servletInitializer.configure(builder);
        verify(builder, times(1)).sources(Application.class);
    }

}