# REST API project using Spring Boot and TDD: Bakery stock management

#### **_Project developed as part of Bootcamp Java #2, by <a href="https://digitalinnovation.one/">Digital Innovation One</a> and <a href="https://www.linkedin.com/company/gft-group/">GFT Group</a>._**

### Overview

The purpose of this project was to develop an API REST using Spring Framework and TDD methodology for managing products of a bakery stock, e.g. bread and cookies, to help my wife managing her bakery business products stock.

### Functionalities Implemented

- [x] Create, read, update and delete services for bakery products, through a REST API with **Level Two** maturity according to Richardson Maturity Model;
- [x] Data validation decoupled from entities using DTO pattern;
- [x] Service to increment stock quantity of a product by id;
- [x] Validation if requested increment respects max stock for the specified product;
- [x] Persistence of all product data received through API HTTP requests;
- [x] Development applying TDD and BDD (given/then/when) methodologies;
- [x] Throw of custom exceptions, including product already exists, not found and stock exceeded;
- [x] Unit tests for all services and exceptions implemented;
- [x] Configuration of two exchangeable environments: DEV and PROD;

### TODO

- [ ] Implement service to decrement stock quantity of a product by id, and the corresponding validations;

### Technologies Used

- Java 11 (development language);
- Maven (dependency management);
- Intellj IDEA Community Edition (IDE);
- Git and GitHub (source code versioning control);
- H2 database (in-memory persistence on DEV environment);
- PostgreSQL database (persistence on PROD environment);
- Heroku cloud-platform (application deployment);

### Dependencies

Spring Boot project was created using Spring Initializr. Required dependencies are listed below and can also be found on this [link](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.2.RELEASE&packaging=jar&jvmVersion=11&groupId=com.projects.dev.tulio&artifactId=springboot-bakery-stock-api&name=springboot-bakery-stock-api&description=REST%20API%20using%20Spring%20Boot&packageName=com.projects.dev.tulio.springboot-bakery-stock-api&dependencies=devtools,lombok,web,data-jpa,h2,postgresql):
- Spring Web
- Spring Data JPA
- Lombok
- PostgreSQL Driver
- Spring Boot DevTools
- H2 Database

External dependencies required for this project:
- [Mapstruct](https://mapstruct.org/) (mapping between entities and DTO classes)

### Execution

For running the project locally, simply type the following command on a terminal:

- **Development** profile:
```shell script
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

- **Production** profile:
```shell script
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
> **_NOTE:_** Remember to change the **prod** configuration file using your own production environment settings.

After application is running, open your preferred web browser and visit the following address to access the API:

- API endpoint for CRUD services: [http://localhost:8080/api/v1/products](http://localhost:8080/api/v1/products)
- API endpoint for stock increment: [http://localhost:8080/api/v1/products/{id}/increment](http://localhost:8080/api/v1/products/{id}/increment)
> **_NOTE:_** Remember to change **{id}** for the corresponding product id.

### Testing

For running the complete test suite, just run the following command:

```shell script
mvn clean test
```

### Useful Links

- [Intellij Shortcuts Pallete](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
- [Spring Official Site](https://spring.io/)
- [JUnit 5 Official Site](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Official Site](https://site.mockito.org/)
- [Tests with Spring Boot](https://www.baeldung.com/spring-boot-testing)
- [REST Architectural Standard Reference](https://restfulapi.net/)
- [Richardson Maturity Model](https://restfulapi.net/richardson-maturity-model/)
- [Martin Fowler's Test Pyramid](https://martinfowler.com/articles/practical-test-pyramid.html#TheImportanceOftestAutomation)
- [Spring Boot App Deploy on Heroku](https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku)
- [TDD in 5 Steps](https://www.xenonstack.com/blog/test-driven-development-python)
- [BDD Given-When-Then Style](https://martinfowler.com/bliki/GivenWhenThen.html)

### Acknowledgments

Many thanks to [Digital Innovation One](https://www.linkedin.com/company/digitalinnovation-one/) and [GFT Group](https://www.linkedin.com/company/gft-group/) for this great course, which provided the foundations for developing this project!

Special thanks also for the course instructors:
- [Rodrigo D'Agostini Peleias](https://www.linkedin.com/in/rodrigopeleias/)
- [Daniel Robert Costa](https://www.linkedin.com/in/danielrc/)
- [Camila Cavalcante](https://www.linkedin.com/in/cami-la/)
- [Rodrigo Tassini](https://www.linkedin.com/in/rodrigo-tassini-2b37699/)
- [João Paulo Oliveira Santos](https://www.linkedin.com/in/desenvolvedorjoaopaulo/)
- [Otávio Reis Perkles](https://www.linkedin.com/in/operkles/)