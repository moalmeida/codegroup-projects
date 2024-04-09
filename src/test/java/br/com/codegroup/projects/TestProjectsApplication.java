package br.com.codegroup.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestProjectsApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestProjectsApplication.class).run(args);
	}

}
