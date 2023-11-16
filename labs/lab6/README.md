# CMPE 172 - Lab #6 Notes

For this Lab, used these to Configure my Project:
- **Project: Maven Project**
- **Language: Java Language (JDK 11)**
- **Spring Boot Version: (2.7.8)**

## The /labs/lab6 folder includes:
- spring-gumball-v3
- spring-jdbc
- spring-mysql
- images (screenshots)
- README.md (lab notes)

## Spring JDBC
**Professors Instructions:**
1. Start a Spring IntelliJ Project
    - Include these Dependencies: JDBC API, H2 Database

2. Create a Customer Object
- Customer.java
    - The simple data access logic you will work with manages the first and last names of customers. To represent this data at the application level, create a Customer class.

```sh
public class Customer {
  private long id;
  private String firstName, lastName;

  public Customer(long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }

  // getters & setters omitted for brevity
}
```
3. Store and Retreieve Data
- Spring provides a template class called JdbcTemplate that makes it easy to work with SQL relational databases and JDBC. Most JDBC code is mired in resource acquisition, connection management, exception handling, and general error checking that is wholly unrelated to what the code is meant to achieve. The JdbcTemplate takes care of all of that for you. All you have to do is focus on the task at hand.

- SpringJdbcApplication.java

```sh
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringJdbcApplication implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(SpringJdbcApplication.class);

  public static void main(String args[]) {
    SpringApplication.run(SpringJdbcApplication.class, args);
  }

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... strings) throws Exception {

    log.info("Creating tables");

    jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
    jdbcTemplate.execute("CREATE TABLE customers(" +
        "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

    // Split up the array of whole names into an array of first/last names
    List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
        .map(name -> name.split(" "))
        .collect(Collectors.toList());

    // Use a Java 8 stream to print out each tuple of the list
    splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

    // Uses JdbcTemplate's batchUpdate operation to bulk load data
    jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

    log.info("Querying for customer records where first_name = 'Josh':");
    jdbcTemplate.query(
        "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
        (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
    ).forEach(customer -> log.info(customer.toString()));
  }
}
```
Commands:
- mvn package
- mvn spring-boot:run

![jdbc sample output](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/jdbc-sample-output.png)

## Spring MySQL
**Professors Instructions:**
1. Start a Spring IntelliJ Project
    - Include these Dependencies: Spring Web, Spring Data JPA, MySQL Driver

2. Create a MySQL Database
  - Run on Docker
  - Note: DB root password is cmpe172
  - This connects to MySQL as root and allows access to the user from all hosts. This is not the recommended way for a product server.

Commands:
- make sure the commands below run in your terminal
- enter each command on it's own line

```sh
docker run -d --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0
```

- Docker command to access MySQL Container:

```sh
docker exec -it mysql bash
```

- Create Database
    - To create a new database, run the following commands at the mysql prompt:

```sh
mysql> create database db_example; -- Creates the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
```
![mysql database commands](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/mysql-database-commands.png)

3. Create the application.properties File
- Spring Boot gives you defaults on all things. For example, the default database is H2. Consequently, when you want to use any other database, you must define the connection attributes in the application.properties file.

- Create a resource file called src/main/resources/application.properties, as the following listing shows:
    - application.properties

```sh
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword
```

- I changed the application.properties because the HMAC key was not included and the username and password was different from the one the professor provided. 

```sh

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example
spring.datasource.username=root
spring.datasource.password=cmpe172
spring.datasource.platform=mysql

# HMAC KEY
hmac.key=${HMAC_KEY:kwRg54x2Go9iEdl49jFENRM12Mp711QI}
```

- Here, spring.jpa.hibernate.ddl-auto can be none, update, create, or create-drop. See the Hibernate documentation for details.

```sh
none: The default for MySQL. No change is made to the database structure.

update: Hibernate changes the database according to the given entity structures.

create: Creates the database every time but does not drop it on close.

create-drop: Creates the database and drops it when SessionFactory closes.
```

- You must begin with either create or update, because you do not yet have the database structure. After the first run, you can switch it to update or none, according to program requirements. Use update when you want to make some change to the database structure.

- The default for H2 and other embedded databases is create-drop. For other databases, such as MySQL, the default is none.

```sh
It is a good security practice to, after your database is in a production state, set this to none, revoke all privileges from the MySQL user connected to the Spring application, and give the MySQL user only SELECT, UPDATE, INSERT, and DELETE.
```

4. Create the @Entity Model
- Hibernate automatically translates the entity into a table.
  - User.java

```sh
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
```

5. Create the Repository
- UserRepository.java
  - You need to create the repository that holds user records. Spring automatically implements this repository interface in a bean that has the same name (with a change in the case — it is called userRepository).

```sh
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
```

6. Create a Controller
- MainController.java
```sh
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UserRepository userRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String name
      , @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }
}
```
7. Test the Application
- Now that the application is running, you can test it by using curl or some similar tool. You have two HTTP endpoints that you can test:
- GET localhost:8080/demo/all: Gets all data. POST localhost:8080/demo/add: Adds one user to the data.
- Add User:

```sh
curl localhost:8080/demo/add -d name=First -d email=someemail@someemailprovider.com
```

```sh
curl 'localhost:8080/demo/all'
```

8. Make Some Security Changes
- When you are on a production environment, you may be exposed to SQL injection attacks. A hacker may inject DROP TABLE or any other destructive SQL commands. So, as a security practice, you should make some changes to your database before you expose the application to your users.

- The following command revokes all the privileges from the user associated with the Spring application:

```sh
mysql> revoke all on db_example.* from 'springuser'@'%';
```

- Now the Spring application cannot do anything in the database.

- The application must have some privileges, so use the following command to grant the minimum privileges the application needs:

```sh
mysql> grant select, insert, delete, update on db_example.* to 'springuser'@'%';
```

- Removing all privileges and granting some privileges gives your Spring application the privileges necessary to make changes to only the data of the database and not the structure (schema).

- When you want to make changes to the database:

```sh
Regrant permissions.

Change the spring.jpa.hibernate.ddl-auto to update.

Re-run your applications.
```
- Then repeat the two commands shown here to make your application safe for production use again. Better still, use a dedicated migration tool, such as Flyway or Liquibase.

Commands:
- docker run -d --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0
- docker exec -it mysql bash
- mysql --password (for this when the password prompt appears, the password field is cmpe172)
- create database db_example; -- Creates the new database
- create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
- grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
- exit - this exits out of sql
- curl localhost:8080/demo/add -d name=First -d email=someemail@someemailprovider.com
- curl 'localhost:8080/demo/all'
- revoke all on db_example.* from 'springuser'@'%'; - for security changes which revokes all privileges
- grant select, insert, delete, update on db_example.* to 'springuser'@'%'; - this gives back minimal privileges

The prompt "Saved" appears because we curl the user which sets the name and email and returns "Saved."

![curl save](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/curl-save.png)

- Now that we curl all, the users information is displayed.

![curl all](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/curl-all.png)

- This is inserting the sql commands to revoke and grant privileges. 

![security changes](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/security-changes.png)

## Spring Gumball (Version 3)
**Professors Instructions:**
1. Create a "Version 3" of your Spring Gumball (based on your implementation of Version 2 from the past lab). In Version 3, add "Spring JPA with MySQL" and map Spring Gumball Domain Object to MySQL.

2. Make the "serialNumber" unique in the Database and pre-configure your Spring Java code to look for a specific Serial Number in the DB based on a Static String of Configuration.

```sh
class GumballModel {

    private String modelNumber ;
    private String serialNumber ;
    private Integer countGumballs ;
    
}
```

3. Change the Gumball Controller to update the Gumball Inventory in the DB and also update the HTML view to display the Model Number and Serial Number fetched from the DB.

## Hints on Running Spring Gumball with MySQL in Docker Containers
- Sample application.properties file:

```sh
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/cmpe172
spring.datasource.username=root
spring.datasource.password=cmpe172
```

- Note that in your application.properties file, the data source connection defaults to "localhost" if there is no setting for MYSQL_HOST environment variablle. This works when MySQL is running on your local machine, or is published via your Local Docker on the standard port 3306 when you run:

```sh
docker run -d --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0
```

- Unfortunately, this will not work when your Spring Gumball App runs inside docker. This is because each Docker Container is isolated like a "Virtual Machine" from other Containers. Thus, you must reference the MySQL's HOST Name or IP from the Spring Gumball Container in order to connect to the Database. Connection to "localhost" from Spring Gumball will not work since there's no MySQL DB running on the same "VM" as the Spring Gumball Container.

- Use the following Docker Run command to start your MySQL Container in an isolated Network named "gumball". Note: create the network if it doesn't exist yet.

```sh
docker network create --driver bridge gumball
```

```sh 
docker run -d --network gumball --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0
```

- Then, run the Spring Gumball App in Docker on the same "gumball" network passing in an environment setting to thell Spring JPA to connect to MySQL via the hostname "mysql". The "hostname" for a container is the name it was launched with.

```sh
docker run --network gumball -e "MYSQL_HOST=mysql" --name spring-gumball -td -p 8080:8080 spring-gumball  
```

- For Docker Compose, you can use the following Manifest.

```sh
version: "3"

services:
  mysql:
    image: mysql:8.0
    volumes:
      - /tmp:/tmp
    networks:
      - network   
    ports:
      - 3306    
    networks:
      - network
    environment:
      MYSQL_ROOT_PASSWORD: "cmpe172"
    restart: always     
  gumball:
    image: spring-gumball
    depends_on:
    - mysql    
    volumes:
      - /tmp:/tmp
    networks:
      - network   
    ports:
      - 8080    
    environment:
      MYSQL_HOST: "mysql"
    restart: always     
  lb:
    image: eeacms/haproxy
    depends_on:
    - gumball
    ports:
    - "80:5000"
    - "1936:1936"
    environment:
      BACKENDS: "gumball"
      BACKENDS_PORT: "8080"
      DNS_ENABLED: "true"
      COOKIES_ENABLED: "false"
      LOG_LEVEL: "info"
    networks:
      - network

volumes:
  schemas:
    external: false

networks:
  network:
    driver: bridge
```

For the above code, I altered it and used 

```sh
version: "3"

services:
  mysql:
    image: mysql:8.0
    platform: linux/amd64
    volumes:
      - /tmp:/tmp
    networks:
      - network
    ports:
      - 3306
    environment:
      MYSQL_ROOT_PASSWORD: "cmpe172"
      MYSQL_DATABASE: db_example
      MYSQL_USER: "dbuser"
      MYSQL_PASS: "dbpass"
    restart: always
  gumball:
    image: spring-gumball-v3
    platform: linux/amd64
    depends_on:
      - mysql
    volumes:
      - /tmp:/tmp
    networks:
      - network
    ports:
      - 8080
    environment:
      MYSQL_HOST: "mysql"
    restart: always
  lb:
    image: eeacms/haproxy
    platform: linux/amd64
    depends_on:
      - gumball
    ports:
      - "80:5000"
      - "1936:1936"
    environment:
      BACKENDS: "gumball"
      BACKENDS_PORT: "8080"
      DNS_ENABLED: "true"
      COOKIES_ENABLED: "false"
      LOG_LEVEL: "info"
    networks:
      - network

volumes:
  schemas:
    external: false

networks:
  network:
    driver: bridge
```

Commands:
- mvn package - creates jar file
- mvn spring-boot:run - runs project on port 80
- docker run -d --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0
- docker exec -it mysql bash
- mysql --password
- USE db_example; (IN SQL)
- show tables; (IN SQL)
- select * from gumball_model; (IN SQL)
- exit
- docker build - builds docker image
- docker run -d --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0
- docker network create --driver bridge gumball
- docker network create --driver bridge gumball - starts the container
docker run -d --network gumball --name mysql -td -p 3306:3306 -e  MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0 - creates the networks if it DNE
- docker run --network gumball -e "MYSQL_HOST=mysql" --name spring-gumball -td -p 8080:8080 spring-gumball - runs the Spring Gumball App on the same network environment

- For the username and password, I entered root in the username field and cmpe172 for the password field. 

![login](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/login.png)

![gumball](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/gumball.png)

- In my terminal, I accessed mysql and displayed the table from gumball_model which showed how many gumballs, the ID number, model number, and serial number.

![sql gumball](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/sql-gumball.png)

![docker mysql](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/docker-mysql.png)

- On the bottom of the screen shows the HMAC and Server/IP address :

![hash server](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/hash-server.png)

- On the bottom of the screen I changed the code to show the Model Number and Serial Number (unique) :

![model serial](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/model-serial.png)

- Docker image is being built

![docker build](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/docker-build.png)

- Docker running image that is running a container

![docker container](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/container.png)

- We can see that spring-gumball and mysql:8.0 are running

![docker ps](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab6/screenshots/docker-ps.png)

