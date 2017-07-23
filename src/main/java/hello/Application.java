package hello;


import hello.dao.VetRepository;
import hello.entity.Vet;
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

//    @Bean
//    public void createNewVet(VetRepository vetRepository){
//
//        vetRepository.save(new Vet("Jerzy", "Kowalski", "ogólny"));
//
//    }
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


