package hello;

import hello.dao.PetRepository;
import hello.entity.Pet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Created by Pawel on 2017-07-21.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

//    @Bean
//    public CommandLineRunner demo(PetRepository repository) {
//        return (args) -> {
//            // save a couple of customers
//            repository.save(new Pet("Doggo", "Bauer", "dog", "male", null,null));
//            repository.save(new Pet("Catto", "O'brian", "cat", "female", null,null));
//
//            // fetch all customers
//            System.out.println("all pets");
//            for (Pet pet : repository.findAll()) {
//                System.out.println(pet.toString());
//            }
//            System.out.println("Znaleziono zwierzaka:"+repository.findByName("Doggo"));
//        };
//    }
}


