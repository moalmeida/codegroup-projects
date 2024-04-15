package br.com.codegroup.projects;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.mockito.Mockito.*;

class ServletInitializerTest {

    @Test
    void configure() {
        SpringApplicationBuilder builder = mock(SpringApplicationBuilder.class);
        ServletInitializer servletInitializer = new ServletInitializer();
        servletInitializer.configure(builder);
        verify(builder, times(1)).sources(Application.class);
    }

}
