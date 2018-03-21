# Pet Clinic

Simple Spring Boot application.

## Description

Idea was taken from example Spring application Pet Clinic. In this web application as a user you can see your pet status and as a vet you can add pets, vets and modify their properties. Every code review and criticism is welcome.

You can see working demo [here](http://petclinic.pawfa.usermd.net:8098). You can create your own account and then login as vet to add your pet with credentials: - email: albus.dumbledore@petclinic.com password: albus.

## Getting Started

### Technology Stack
Component         | Technology
---               | ---
Frontend          | [Thymeleaf](https://www.thymeleaf.org/)
Backend           | [SpringBoot](https://projects.spring.io/spring-boot) (Java)
Security          | Spring Security
In Memory DB      | H2
Persistence       | JPA (Using Spring Data)
Build Tools       | Maven(Java)

### Dependencies

- Java 8
- Maven 3.3.9+

### Installing

To install this application, run the following commands:
```bash
git clone git@github.com:pawfa/petclinic.git
cd petclinic
```
This will get a copy of the project installed locally.

### Executing program

To run the program, cd into the `petclinic` folder and run:
 
```bash
mvn package && java -jar target/petclinic-1.0-SNAPSHOT.jar
```

