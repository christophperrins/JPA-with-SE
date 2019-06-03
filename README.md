# JPA in SE

This is a 4 part piece in which I go through:

- [**Java Persistence API (JPA)**](https://github.com/christophperrins/JPA-with-SE)
- [Content and Dependency Injection (CDI)](https://github.com/christophperrins/CDI-with-SE)
- [Rest Services in Java EE](https://github.com/christophperrins/REST-with-EE)
- [Combining Rest services, CDI and JPA](https://github.com/christophperrins/Java-EE-Backend)

## What's in this chapter?

- [How to add Entities to a database using JPA](https://github.com/christophperrins/JPA-with-SE/tree/master/AddingAccountToDatabase)
- [How to build relationships between entities using JPA](https://github.com/christophperrins/JPA-with-SE/tree/master/AddingAccountWithTasks)
- Where to place the persistence.xml

## Prerequisite 

You will need to have a local h2 database running
- Download the [H2 Database jar](http://repo2.maven.org/maven2/com/h2database/h2/1.4.199/h2-1.4.199.jar)
- Execute the jar file `$ java -jar h2-1.4.199.jar`


## Why does this look different to other Java EE applications?

This example is a Java SE application.
It will use slightly different syntax to do things as I have tried to keep JPA totally separate from the REST and CDI libraries. 

