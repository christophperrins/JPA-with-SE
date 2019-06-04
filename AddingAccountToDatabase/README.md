# src

Files of note: 
- Account
- AccountRepositoryDB
- AccountTest
- persistence.xml

## Account
**Why the @Entity?**

Well we have transient objects (instances of classes which are saved in memory). And we want to take this object and save it forever. Well to tell the JPA where to find which fields to make etc we give it a nice little @Entity annotation to look for.

**Why the @GeneratedValue**

Well we don't want to keep track of what id to put in next ourselves. We can get the JPA or the database to do that for us!

**Whats going on with the constructors?**

Account() {} is needed as this gets called later when creating an Entity object from the database. This is done by the JPA, it will always know what it is called (same as class) and it doesn't need any parameters.

*Then why is the Account() {} overloaded with  Account(String firstName, String lastName, int accountNumber)?*

Well during testing we want to be able to create an object and directly assign a value to each variable. Its going to look silly and long to create an Object and then run each setter for each - it could take all blinking day to read!

*Why doesn't either constructor include the id?*

Well it gets set by the database later so I didn't see much point to be honest. 


## AccountRepositoryDB
*Why the random interface thrown in?*

Well this is more for later - but its all about Dependency Inversion. It is better to have methods coming from an interface. If a concrete class calls a concrete class, calls a concrete class -> they are highly coupled and its hard to swap out implementations! Take a copy and paste function. Maybe you want to read from ctrl+c and paste with ctrl+v. Well thats very nice and well, but maybe we want to read from a scanner! We don't want to 

## AccountTest

## persistence.xml

---

# pom.xml

Dependencies of note:
- Hibernate


## What is Hibernate?

Java Persistance API is a set of specifications.

These specifications are implemented by many libraries - of which Hibernate is one of them. It is by no means "special", it is just the one which has been round the longest.