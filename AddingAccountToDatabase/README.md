<a name ="Index"/>

# Index

This project consists of:
- [Account](#Account)
- [AccountRepositoryDB](#AccountRepositoryDB)
- [persistence.xml](#persistence.xml)
- [AccountTest](#AccountTest)
- [pom.xml](#pom.xml)

# 1. src
<a name ="Account"/>

## 1.1. Account
[Back To Top](#Index)
### 1.1.1. Why the @Entity?

Well we have transient objects (instances of classes which are saved in memory). And we want to take this object and save it forever. Well to tell the JPA where to find which fields to make etc we give it a nice little @Entity annotation to look for.

### 1.1.2. Why the @GeneratedValue

Well we don't want to keep track of what id to put in next ourselves. We can get the JPA or the database to do that for us!

### 1.1.3. Whats going on with the constructors?

Account() {} is needed as this gets called later when creating an Entity object from the database. This is done by the JPA, it will always know what it is called (same as class) and it doesn't need any parameters.

*Then why is the Account() {} overloaded with  Account(String firstName, String lastName, int accountNumber)?*

Well during testing we want to be able to create an object and directly assign a value to each variable. Its going to look silly and long to create an Object and then run each setter for each - it could take all blinking day to read!

*Why doesn't either constructor include the id?*

Well it gets set by the database later so I didn't see much point to be honest. 

<a name ="AccountRepositoryDB"/>

## 1.2. AccountRepositoryDB
[Back To Top](#Index)
### 1.2.1. Why the random interface thrown in?

Well this is more for later - but its all about Dependency Inversion. It is better to have methods coming from an interface. If a concrete class calls a concrete class, calls a concrete class -> they are highly coupled and its hard to swap out implementations! Take a copy and paste function. Maybe you want to read from ctrl+c and paste with ctrl+v. Well thats very nice and well, but maybe we want to copy from a handheld-scanner and paste to a screen! It would be frustrating to write everything again - so we make our components as substitutable as possible. We can do this by coding to an interface, and just using the interfaces methods. When we call the method, we feed it the concretion.

### 1.2.2. What is the EntityManagerFactory and all the class variables?

For now all you really need to know is that `Persistence` come from the JPA library. With it we can create a factory class of EntityManager's which we need to feed it a *Pesistence Unit* (it grabs the persistence unit from the persistence.xml, and in there mine is named "myPU", so I am now stating that I want to use "myPU" here). 
 

### 1.2.3. Whats EntistyManager?

It manages Entities.

### 1.2.4. Whats EntityTransaction?

It manages transactions - either all things are done, or none of them are completed

<a name ="persistence.xml"/>

## 1.3. persistence.xml
[Back To Top](#Index)
### 1.3.1. Can I put my persistence.xml elsewhere?

For java SE projects, the META-INF directory must be held within the src/main/resources directory. The persistence.xml must be within the META-INF directory.

### 1.3.2. How did I create the persistence tag attributes?

I didn't, you shouldn't. Use a template.

### 1.3.3. What different transaction-types are there?

I know of two, JTA and RESOURCE_LOCAL. RESOURCE_LOCAL is more for when you are working on a single machine, connecting to a locally held database. If i get time I'll add details about JTA.

### 1.3.4. Can you explain the properties?

#### 1.3.4.1. The first four properties 
These are to authenticate yourself. Just like when you connect to databases directly through JDBC, it still needs to know which user and password, which database to use, how to talk to it (org.h2.Driver).

#### 1.3.4.2. Hibernate.dialect 
This is how we can tell our JPA which 'dialect' to use. English is a language, however it has many dialects - you have Cockney, Scouse and Brummie etc. They all use the same base language with a few unique twangs in that dialect. Lets look at a coding example, here is how you can add a primary key in MySQL and Oracle (two SQL databases):

**MySQL**
>CREATE TABLE Persons (\
>&nbsp;&nbsp; ID int NOT NULL,\
>&nbsp;&nbsp; LastName varchar(255) NOT NULL,\
>&nbsp;&nbsp; FirstName varchar(255),\
>&nbsp;&nbsp; Age int,\
>&nbsp;&nbsp; PRIMARY KEY (ID)\
>);

**Oracle**
>CREATE TABLE Persons (\
>&nbsp;&nbsp; ID int NOT NULL PRIMARY KEY,\
>&nbsp;&nbsp; LastName varchar(255) NOT NULL,\
>&nbsp;&nbsp; FirstName varchar(255),\
>&nbsp;&nbsp; Age int\
>);

Both databases use the Structured Query Language, but they have slight differences known as their dialect. Our JPA needs to know which database we are talking to, so it can speak the same dialect as the database.

#### 1.3.4.3. Hibernate.hbm2ddl.auto

When we connect to the database, and we have an EntityManager which will send items to it, it will need to run some Data Definition Language first (Creating tables etc.). We can tell it just to create tables, updates tables, create-drop tables etc.  

<a name ="AccountTest"/>

## 1.4. AccountTest
[Back To Top](#Index)

These tests were created first before any piece of code. I didn't have an account, but i knew it needed a getFirstName, getLastName and getAccountNumber(). It then gave an error saying I don't have an Account class - thats good! Red, Green, Refactor.

### 1.4.1. CreateAccount
This just tests that an instance of account can be created with the data it needs.

### 1.4.2. persistAccount
When I first created an account, I did not give it an id. I sent values in for firstname, lastname and accountNumber. It would therefore default so a null value (but because it is a class variable of a primitive data type, it gets a default value of 0).

So this is the thing I test for first, that the id is 0. I then persist it to the database. When it persists to the database, my Account entity is going to be auto generating an id for me. This entity is now checked to make sure that the id has been updated.

### 1.4.3. findAccounts
This adds account item into the database. JPA takes the java object, uses the Object Relational Mapping to turn it into a state that the database will understand and then send it off to it. When we persist the account entity, it doesn't have an id. When it persists, it gets an id which is automatically generated. We then grab the id and look in the database for it, which will hopefully respond the same object.

### 1.4.4. updateAccount
This one is a doosy.
I add an account to my database with firstname, lastname and accountNum of Chris, Perrins and 0. \
I then create a new Account with values Corazon, Gaza, 123 (THIS account i create never goes into the database!). What happens in the method is that each account information inside the chris object gets overriden with the Corazon object. The only variable which isn't overridden is the id.

We **do not persist** again, because we **do not** want another record. 

When we change the chris object, the changes will automatically be changed in the database via the JPA as long as we commit the changes.

If you don't believe me, at the end of the test, print out the id of 'account' and 'newinfo'. newinfo will have a id of 0 -> because it never gets added!

### 1.4.5. deleteAccount
Here we add an account to the database.\
Make sure that an id has been automatically generated (assertFalse id == 0)
delete it from the database
look for the account in the database\
The return object should be null as the account should no longer be there

---

<a name ="pom.xml"/>

# 2. pom.xml
[Back To Top](#Index)

Dependencies of note:
- Hibernate


## 2.1. What is Hibernate?

Java Persistance API is a set of specifications.

These specifications are implemented by many libraries - of which Hibernate is one of them. It is by no means "special", it is just the one which has been round the longest.