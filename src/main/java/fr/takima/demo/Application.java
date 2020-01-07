package fr.takima.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.takima.demo.model.Temperature;
import fr.takima.demo.service.TemperatureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 *
 */
@ComponentScan
@EnableJpaRepositories
@EnableAutoConfiguration
@SpringBootConfiguration
public class Application {

  public static void main(String[] args) {
    new SpringApplicationBuilder(Application.class)
        .run();
  }
}
