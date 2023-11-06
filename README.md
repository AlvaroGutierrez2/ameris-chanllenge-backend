# ameris-chanllenge-backend

## Requirements
For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Lombok](https://projectlombok.org/)

## Considerations: before run the application
H2 is the database that is used by this project, the tables and the data is being created and inserted at moment to deploy the application and 
i will be only keep in memory.
The file used for create the table and populate the data are:
- [Schema.sql](https://github.com/AlvaroGutierrez2/ameris-chanllenge-backend/blob/main/src/main/resources/schema.sql)
- [Data.sql](https://github.com/AlvaroGutierrez2/ameris-chanllenge-backend/blob/main/src/main/resources/data.sql)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.komet.challenge.ChallengeApplication` class from your IDE.

Alternatively you can use in the root directory the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```
mvn spring-boot:run
```

## H2 console
Once the application is running you can check the H2-console here:
```shell
http://localhost:8080/h2-console/

Credentials
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:challangedb
User Name: root
Password: No password is necesary
```


## Endpoint

This endpoint does not need any parameters and returns all the employee's information from the DataBase
- http://localhost:8080/api/v1/employees

This endpoint needs {employeeId} and it returns all for the information for the specific employee id.  
- http://localhost:8080/api/v1/employee/{employeeId}
