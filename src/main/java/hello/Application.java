package hello;


import hello.dao.VetRepository;
import hello.entity.Vet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by Pawel on 2017-07-21.
 */
@SpringBootApplication
@PropertySource({"classpath:application.properties"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}


