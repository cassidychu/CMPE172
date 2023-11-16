# CMPE 172 Project Journal

# Journal Entry 1 (March 16, 2023):
- I cloned the GitHub repository that was provided by Professor Paul Nguyen for the Starbucks Project.
- I added the update with the mobile app JAR and Makefile that was provided by Professor Paul Nguyen to the project.

[Journal Entry 1 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/8ff1001d76b82d791e84fa3138df13f0339978c) 

# Journal Entry 2 (April 16, 2023):
- I added more commands to the Makefile for MySQL and Docker.
- I tested the folders from starbucks-api and starbucks-nodejs
- Steps:
    1. Open two separate terminals
        - In one terminal use the command: make run for starbucks-api
        - In the second terminal simultaneously run starbucks-nodejs using the command: make run
        - I ran into the issue when running starbucks-nodejs. An internal error of Error: Cannot find module 'express' appeared. 
        - To resolve Error: Cannot find module 'express', I used the command:
         ```sh
        npm install express
        ```
        - Once you type in 'npm install express' in the starbucks-nodejs terminal, re-run the code: make run and it the run should be successful.
    2. Open localhost:9090, this port is dedicated to the Starbucks project and you should see the "Welcome to Starbucks Reserved" sign along with a store drop-down bar and 3 buttons.

[Journal Entry 2 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/2770f3f45a524ba4d91fb3d8f5f121fe5fd2a3f3)

![starbucks api test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-api-test.png)

![starbucks nodejs test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-nodejs-test.png)

![starbucks api nodejs test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-api-nodejs-test.png)

![docker ps](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/docker-ps.png)

# Journal Entry 3 (April 24, 2023):
- I added in spring-cashier from the midterm.
- The dependencies that were needed in spring-cashier were:
    - Spring Data JPA, Spring Security, Thymeleaf, Validation, Spring Web, MySQL Driver, Lombok, and Test Containers.
- Updated spring-cashier and removed Spring JPA and mysql dependencies from the midterm so that when "Place Order" is clicked it randomly generates an order.

[Journal Entry 3 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/b854f8153446217f1eb798bf0c9e25349f60bd4b)
[Journal Entry 3 Pt 2 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/7834b82a197ca64d00f23eba9c5e93ada629fde8)

- This is running spring cashier using the command "make run."

![spring cashier running](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/spring-cashier-running.png)

- Placed order randomly generated orders, but when clicking on "Get Order" it does nothing. 

![spring cashier proof](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/spring-cashier-proof.png)

# Journal Entry 4 (April 25, 2023):
-  I spent my time working on Lab 7 trying to fix an error after implementing spring-cybersource.
- The error went away after realizing I forgot to add the cybersource key and id in application.properties.
- I tested spring-cybersource and then checked on the cybersource website that the transactions went through.
- After everything tested worked, I moved the spring-cybersource folder into the spring-payments folder. 
- I have a new error but will fix it tomorrow.
- I was working on lab 7 because it will be used in the final project.

[Journal Entry 4 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/2494529c7cf38a2512a6b2773af5e5d67906907f)

![lab seven payments controller](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/lab-seven-payments-controller.png)

# Journal Entry 5 (April 26, 2023):
- I worked a bit on Lab 7 and tried to fix the error even after implementing cybersource. I will work more on it tomorrow since I spent had a midterm today and was exhausted from studying leading up to the midterm. 

[Journal Entry 5 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/tree/9a306416edccf06d5c1c9afc831c9d2124f7c0b7)

# Journal Entry 6 (April 27, 2023):
- I spent a majority of today looking through the past labs and organizing which ones I will be needing for the project. I have been look through some of the recordings about the project and planning how I will be distributing the workload and what needs to be implemented. 
- Right now I currently have myself set up to work in the starbucks-api directory.

[Journal Entry 6 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/80eaddb2a7ec8a9262616976033fc4a9f412fd55)

![starbucks api directory](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-api-directory.png)

# Journal Entry 7 (April 28, 2023):
- I revisted Lab 6 - Spring Data to follow the steps of creating a database. 
- Under the projects folder I used the commands:
    - make starbucks-app-run: 
	    java -cp starbucks-app.jar \
            -Dapiurl="http://localhost:8080" \
            -Dapikey="2742a237475c4703841a2bf906531eb0" \
            -Dregister="5012349" \
            starbucks.Main 2>debug.log
    - login 
- This showed the num pad which I entered login that bypassed the pin and showed the cards.
- I spent today looking through the code and figuring out which steps I need to take as well as worked on Spring-Payments lab.

[Journal Entry 7 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/c127584175ef8d71c976357ab804a772d74d558d)

![starbucks keypad](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-keypad.png)

Commands:
- docker run -d --name mysql-starbucks -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0 (in the starbucks-api directory)

![starbucks api docker run](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-api-docker-run.png)

# Journal Entry 8 (April 29, 2023):
- Jackson was added to dependencies in the pom file of the spring-cashier folder.

```sh
<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.13.3</version>
		</dependency>
```
- Added more options for the drinks, milk, and size in the starbucks.html file in spring-cashier.

```sh
 <div align="center">
      <!-- SELECT DRINK -->
        <label for="drinks">Select Drink:</label>
        <select name="drinks" id="drinks">
          <option value="Caffe Latte">Caffe Latte</option>
          <option value="Caffe Americano">Caffe Americano</option>
          <option value="Caffe Mocha">Caffe Mocha</option>
          <option value="Espresso">Espresso</option>
          <option value="Cappuccino">Cappuccino</option>
        </select> 
        <!-- SELECT MILK -->
        <label for="milks">Select Milk:</label>
        <select name="milks" id="milks">
          <option value="Whole Milk">Whole Milk</option>
          <option value="2% Milk">2% Milk</option>
          <option value="Nonfat Milk">Nonfat Milk</option>
          <option value="Almond Milk">Almond Milk</option>
          <option value="Soy Milk">Soy Milk</option>
        </select> 
        <!-- SELECT SIZE -->
        <label for="sizes">Select Size:</label>
        <select name="sizes" id="sizes">
          <option value="Tall">Tall</option>
          <option value="Grande">Grande</option>
          <option value="Venti">Venti</option>
          <option value="Your Own Cup">Your Own Cup</option>
        </select> 
    </div>      
```

- Under application.properties in spring-cashier I added in the Starbucks API connection:

```sh
starbucks.api.host=${STARBUCKS_API_HOST:localhost}
starbucks.api.port=${STARBUCKS_API_PORT:8080}
```

- These are the edits I made in SpringCashierController.java in spring-cashier. 
- Imports needed:
```sh
import org.springframework.beans.factory.annotation.Value;
// Imports for HTTP
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//https://fasterxml.github.io/jackson-databind/javadoc/2.7/com/fasterxml/jackson/databind/JsonNode.html
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.lang.InterruptedException;

import lombok.extern.slf4j.Slf4j;
```

![spring cashier imports](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/spring-cashier-imports.png)

```sh
@Value("${starbucks.api.host}")
String starbucksApiHost;
@Value("${starbucks.api.port}")
String starbucksApiPort;
```

```sh
Errors errors, Model model, HttpServletRequest request) throws IOException, JsonProcessingException, InterruptedException {
```

```sh
/* Setup Http request */
    HttpClient client = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();
```
![setting up http request](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/setting-up-http-request.png)

```sh
 Order order = new Order();

            order.setDrink(command.getDrinks());
            order.setMilk(command.getMilks());
            order.setSize(command.getSizes());
            System.out.println(order.toString());

            String orderAsString = objectMapper.writeValueAsString(order);

            HttpRequest apiRequest = HttpRequest.newBuilder()
            //https://www.baeldung.com/java-httpclient-post
                .uri(URI.create("http://" + starbucksApiHost + ":" + starbucksApiPort + "/order/register/" + register))
                .POST(HttpRequest.BodyPublishers.ofString(orderAsString))
                .header("Content-Type", "application/json")
                .build();

            HttpResponse<String> response = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String json = response.body();
            JsonNode jsonNode = objectMapper.readTree(json);
            if(statusCode == 400){
                String errorMessage = jsonNode.get("message").asText();
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + register + "\n" +
                    "Status:   " + errorMessage + "\n" ; 
            } 
            else {
                order = objectMapper.readValue(json, Order.class);	
                System.out.println(order.toString());
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Drink: " + order.getDrink() + "\n" +
                    "Milk:  " + order.getMilk() + "\n" +
                    "Size:  " + order.getSize() + "\n" +
                    "Total: " + order.getTotal() + "\n" +
                    "\n" +
                    "Register: " + order.getRegister() + "\n" +
                    "Status:   " + order.getStatus() + "\n" ;
            }            
```

```sh
  HttpRequest apiRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://" + starbucksApiHost + ":" + starbucksApiPort + "/order/register/" + register))
                .GET()
                .header("Content-Type", "application/json")
                .build();

            HttpResponse<String> response = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String json = response.body();
            JsonNode jsonNode = objectMapper.readTree(json);
            if(statusCode == 400){
                String errorMessage = jsonNode.get("message").asText();
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + register + "\n" +
                    "Status:   " + errorMessage + "\n" ; 
            } 
            else {      
                Order order = objectMapper.readValue(json, Order.class);	
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Drink: " + order.getDrink() + "\n" +
                    "Milk:  " + order.getMilk() + "\n" +
                    "Size:  " + order.getSize() + "\n" +
                    "Total: " + order.getTotal() + "\n" +
                    "\n" +
                    "Register: " + order.getRegister() + "\n" +
                    "Status:   " + order.getStatus() + "\n" ;
            }
```

```sh
HttpRequest apiRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://" + starbucksApiHost + ":" + starbucksApiPort + "/order/register/" + register))
                .DELETE()
                .header("Content-Type", "application/json")
                .build();

            HttpResponse<String> response = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String json = response.body();
            JsonNode jsonNode = objectMapper.readTree(json);
            if(statusCode == 400){
                String errorMessage = jsonNode.get("message").asText();
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + register + "\n" +
                    "Status:   " + errorMessage + "\n" ; 
            } 
            else if(statusCode == 200) {
                String status = jsonNode.get("status").asText();
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + register + "\n" +
                    "Status:   " + status + "\n" ; 
            }
```

- In the starbucks-api folder, I made changes to the application-test.properties, pom.xml, and Dockerfile. 

- application-test.properties:
```sh
# MYSQL 
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/starbucks
spring.datasource.username=root
spring.datasource.password=cmpe172
```

![application test properties april](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/application-test-properties-april.png)

- pom.xml (the version number was just changed):
```sh
</parent>
    <groupId>com.example</groupId>
    <artifactId>spring-starbucks-api</artifactId>
    <version>1.0</version>
    <name>spring-starbucks-api</name>
    <description>spring-starbucks-api</description>
    <properties>
```

- Dockerfile:
```sh
FROM openjdk:11
EXPOSE 8080
ADD ./target/spring-starbucks-api-1.0.jar /srv/spring-starbucks-api-1.0.jar
CMD java -jar /srv/spring-starbucks-api-1.0.jar --spring.profiles.active=test
```

- Commands:
    - make run (in spring-cashier directory) 

[Journal Entry 8 pt 1 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/62a970539b7715163ac3254eb3a64299e0696d2e)

[Journal Entry 8 pt 2 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/633f25185fb4770836aa48e72bb566178768cdac)

![jackson added](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/jackson-added.png)

![spring application properties](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/spring-application-properties.png)

- This shows the generated random drink once place order is pushed. 

![place random drink](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/place-random-drink.png)

- What appears when the order is cleared. 

![clear order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/clear-order.png)

- I ran some tests on Postman.

## POSTMAN
![ping test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/ping-test.png)

![postman cards](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/postman-cards.png)

![postman get card](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/postman-get-card.png)

![postman activate card](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/postman-activate-card.png)

![postman new order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/postman-new-order.png)

![postman pay order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/postman-pay-order.png)

![postman get order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/postman-get-order.png)

![postman clear order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/postman-clear-order.png)

## DOCKER RUN API
![docker run api](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/docker-run-api.png)

## HTML ADJUSTMENTS
![starbucks options](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-options.png)

![drink option](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/drink-option.png)

![size option](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/size-option.png)

# Journal Entry 9 (April 30, 2023):
- Implemented Kong, followed lab 8
- curl ping test
- starbucks mobile app to test everything required for demo
- Kong can connect with Spring Cashier

- Docker under mysql 
    - show databases;
    - use starbucks
    - show tables;
    - select * from starbucks_order;
    - select * from starbucks_cards;

- Commands:
    - spring-cashier directory - make run
    - project - make starbucks-app-run
        - login
        - touch (3,3)
        - touch (2,2)

[Journal Entry 9 pt 1 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/33a9263d702cbdaf6a3530c8ab186dac2a1f7d5f)

[Journal Entry 9 pt 2 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/c67a7ec0aa0205f0b352409fbf7b9ad199aa108a)

![curl ping test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/curl-ping-test.png)

# Journal Entry 10 (May 1, 2023):
[Journal Entry 10 pt 1 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/c9d26c6fa7e9e89b9c0ef88fdd999d8dd65d35c2)

[Journal Entry 10 pt 2 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/222c59f60c8f22ed9fed2a8b84fb7b37ccd84cbd)

[Journal Entry 10 pt 3 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/4c9e491858048657ac3d112b230e47a1758bdb15)

## Docker Demo Day
- I finsihed the docker demo which required:
    **Professors Instructions**
    - Setup Steps:
        1. Show the Starbucks API "Kong API Gateway" endpoint and API being used.  Demo this via a CURL ping test to API.
        2. Show the launch manifest / configuration for Starbucks Cash Register confirming connection to API via Kong with API Key
        3. Start up and Log into your Starbucks Cash Register App (your ported Spring MVC version)
        4. Show the launch command to run Starbucks "Mobile" App with connection to the same Kong API Gateway and API Key
        5. Start up the "Starbucks Mobile App" pointing out the "Store/Register" ID being used
        6. Connect to Backend MySQL Database and query the "New Starbucks Card" created by Starbucks Mobile App
        7. Query to show the Card Number, Card Code and starting Balance in "Activated Status"

    - Demo Steps:
        1. As the "Cashier" logged into Cashier's App, create a new Order for a specific Store / Register (matching the Register used by Starbucks Mobile App)
        2. Query the MySQL DB to show the Order matching that displayed on Cashier's App
        3. From the "Starbucks Mobile App", pay for the Order and point out the remaining "Card Balance" after purchase
        4. Query the MySQL DB to show the status of the Order and Card balance. 
        5. Refresh the Order Page on "Cashier's App" to confirm the Order Status (matching that shown in MySQL DB)

![startbucks sequence diagram](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-sequence-diagram.png)

- I recorded the docker demo explaining each of the required steps above such as added in docker-compose file and did a curl ping test/more. 

**Curl Ping Test**
- In the terminal I ran these commands:
    - curl localhost/api/ping
    -  curl -H "apikey:yourapikeyhere" localhost/api/ping

**Run Docker**
- In the spring-cashier directory I ran these commands:
    - docker compose up
    - docker compose down

**MySQL**
- In Docker I ran these commands:
    - mysql --password
    - password is cmpe 172
    - show tables;
    - use starbucks
    - select * from starbucks_card;
    - select * from starbucks_order;

- I placed the order and checked for the order in the mysql table making sure that the correct register was included and order. Then went into the Starbucks mobile app to get the new card and check it's balance. Which then you can see the balance go down based on how much the selected drink cost. 

**Starbucks Mobile App**
- In the project directory I ran these commands that ran the Starbucks Mobile app:
    - make starbucks-app-run
    - login
    - touch (3,3) 
    - touch (2,2)
    - touch (3,3)

- I went back and forth between Docker to check the MySQL tables and the Starbucks Reserve website to show that the order was place, paid for, recievied, and cleared from the Dub C register in the table.

# Journal Entry 11 (May 7, 2023):
- I've been working on Lab 7 an 8 which I just finished and will be submitting today which play a role in understanding the project. But I will be working on the final parts of the project tomorrow.

[Journal Entry 11 Lab 7 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/e475ea959167d0f4466c44576187596ced506825)

[Journal Entry 11 Lab 8 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/6929582e9d7aae0b88e05b379f9ca76d6c0a871d)

# Journal Entry 12 (May 12, 2023):
- Today I made some updates to the code and implemented Lab 8 into the project. Which will help with the project as I have to deploy to Google Cloud Platform. 

[Journal Entry 12 pt 1 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/ae9fc2af049fdf2ad00d268c3644c90d3d168e79)

[Journal Entry 12 pt 2 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/cd1b95b03a692d42d659fb767099f5d2c2cdaeac)

[Journal Entry 12 pt 3 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/19e49e296694da6a8e162e087c824deaa86e1370)

[Journal Entry 12 pt 4 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/28b1a50ac412a09393e9c02a63f4c4a253a0b88e)

[Journal Entry 12 pt 5 History](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/cbdd776a423b8ec169f51a879e7ee52b792f8fc5)

**Creating a Cluster**
- I created a cluster following what was taught in the demo for Lab 8:
    - 1. Create the cluster, I named mine "starbucks-project"
    - 2. Changing the Node Pool
    - 3. I changed my machine type to e2-medium because this project is bigger than the labs so I felt like it needed more.
    - 4. Hit the create cluster button

![created cluster](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/created-cluster.png)

**Google Cloud SQL**
- On the left hand side of the menu, I went to the SQL tab. 

- Rewatching Demo #14 (Spring Gumball on GKE with Cloud SQL) provided by Professor Nguyen helped a lot in the setup process for creating a Cloud SQL MySQL Instance.

- I followed lab 6 README documentation for the setup:
    - Instance ID: mysql8
    - Password: cmpe172
    - Version: MySQL 8.0
    - Region: Any
    - Zone: Single Zone
    - Machine Type: Lightweight
    - Storage: SSD / 10 GB
    - Connections: Private IP
    - Network: default (VPC Native)
        - May require setting up a private service connection
        - a. ENable Service Networking API
        - b. Use Automatic IP Range

![gcp mysql eight](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/gcp-mysql-eight.png)

![workload status green](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/workload-status-green.png)

- In the starbucks-api directory, I ran:

```sh
docker build -t cassidychu/spring-starbucks-api:2.0 .
```

```sh
docker push cassidychu/spring-starbucks-api:2.0 
```

- Be sure that you are using your Docker account and the right version.

![docker build](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/docker-build.png)

**Installing Google Cloud CLI on terminal**
- I personally didn't want to use the shell in Google Cloud Platform (GCP) because that would require me to upload a lot of files. Alternatively I just installed Google Cloud CLI to my terminal.
    - [How to Install Google Cloud Cli on MacOS](https://www.educative.io/answers/how-to-install-google-cloud-cli-on-macos)
    - This requires you to download the compressed file and extract it, then make sure to download it into your directory.

```sh
./google-cloud-sdk/install.sh
```

- Enter y for all questions in the terminal.

```sh
./google-cloud-sdk/bin/gcloud init
```

![download gcloud cli](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/download-gcloud-cli.png)

- Now you can run all the commands you would in the provided shell in GCP.

- In your starbucks-api directory, run the connect to Google Cloud command in the created cluster.

```sh
gcloud container clusters get-credentials starbucks-project --zone us-central1-c --project star-386419
```

**GCP Deployment**
- Once connected run:

```sh
kubectl create -f deployment.yaml --save-config
```

```sh
kubectl create -f service.yaml
```

![deployment and service yaml](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/deployment-and-service-yaml.png)

- Opening a new terminal, go to the jumpbox directory and insert commands:

```sh
kubectl create -f jumpbox.yaml
kubectl get pod jumpbox
kubectl exec -it jumpbox -- /bin/bash
```

- Once you're in jumpbox use these commands:

```sh
apt-get update
apt-get install curl
apt-get install httpie
apt-get install mysql-client
mysql -u admin p -h "INSERT THE IP FROM MYSQL INSTANCE ON GCP" starbucks
```

- You will then be prompted to enter a password, the password is cmpe172. Now that you're in MySQL, enter:

```sh
show tables;
```

![jumpbox sql tables](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/jumpbox-sql-tables.png)

- Back in jumpbox, do a curl ping test to see that everything works.

```sh
curl http://spring-starbucks-api-service:80/ping
```

![jumpbox curl ping](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/jumpbox-curl-ping.png)

**Install Kong GKE Ingress Controller**
```sh
kubectl apply -f https://bit.ly/k4k8s
```

![https apply](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/https-apply.png)

```sh
export KONG="ENTER THE KONG PROXY IP HERE"
echo $KONG 
curl -i $KONG
```

![export kong](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/export-kong.png)

**Create an Ingress rule to proxy the Starbucks Service**
```sh
kubectl apply -f kong-ingress-rule.yaml
```

```sh
kubectl apply -f kong-strip-path.yaml
```

```sh
kubectl patch ingress starbucks-api -p '{"metadata":{"annotations":{"konghq.com/override":"kong-strip-path"}}}'
```

![ingress rule](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/ingress-rule.png)

**Test Kong API Ping Endpoint**
```sh
```

**Add Kong Key-Auth PlugIn**
```sh
kubectl apply -f kong-key-auth.yaml
```

```sh
kubectl patch service spring-starbucks-api-service -p '{"metadata":{"annotations":{"konghq.com/plugins":"kong-key-auth"}}}'
```

**Configure an API Client Key**
```sh
kubectl apply -f kong-consumer.yaml
```

**Create Kubernetes Secret**
```sh
 kubectl create secret generic apikey  \
  --from-literal=kongCredType=key-auth  \
  --from-literal=key=Zkfokey2311
```

**Apply API Key Credentials to API Client**
```sh
kubectl apply -f kong-credentials.yaml
```

**Test Your API Against Kong via Public IP of Load Balancer**
- I ended up using the same apikey given in lab8 and updated the api key in spring-cashier.

```sh
curl $KONG/api/ping
curl $KONG/api/ping --header 'apikey: Zkfokey2311'
```

![test to key](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/test-to-key.png)

![echo kong](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/echo-kong.png)

- Tested that when you go to the Kong IP address on port 80, the Kong error would appear because it doesn't have the api key in it.

![kong error](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/kong-error.png)


# Journal Entry 13 (May 15, 2023):

[Journal Entry 13 History Part 1](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/12a768c340b0d7f47df373643e55a9e3f516651f)

[Journal Entry 13 History Part 2](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/a9543308f5ef941b62c383d1978b4ace6c55a66e)

- I added a new table to the MySQL database, called StarbucksRegister.java that contained an id and the register. I included a foregin key to ensure that it was unique. 

**Creating a new model: StarbucksRegister**

- StarbucksRegister.java
```sh
package com.example.springstarbucksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/* 
    https://www.baeldung.com/intro-to-project-lombok
    https://www.baeldung.com/lombok-ide
            
    Spring JPA References:

    https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html
    https://docs.spring.io/spring-data/jpa/docs/2.5.1/api
    https://www.baeldung.com/spring-data-rest-relationships
    https://www.baeldung.com/hibernate-one-to-many
    https://www.baeldung.com/jpa-one-to-one
    https://www.baeldung.com/jpa-cascade-types
    https://www.baeldung.com/category/persistence/tag/jpa

*/

@Entity
@Table(name = "STARBUCKS_REGISTER")
@Data
@RequiredArgsConstructor
public class StarbucksRegister {

    private @Id
    @GeneratedValue
    @JsonIgnore  /* https://www.baeldung.com/jackson-ignore-properties-on-serialization */
    Long id;
    private String register;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore  /* https://www.baeldung.com/jackson-ignore-properties-on-serialization */
    private StarbucksOrder order;


}
```

**Creating StarbucksRegister Repository**
- I also created a repository for the Starbucks register which finds and deletes the order by its register. 

- StarbucksRegisterRepository.java

```sh
package com.example.springstarbucksapi.repository;

/* https://docs.spring.io/spring-data/jpa/docs/2.4.6/reference/html/#repositories */

import com.example.springstarbucksapi.model.StarbucksRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarbucksRegisterRepository extends JpaRepository<StarbucksRegister, Long> {

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
    // https://docs.spring.io/spring-data/data-commons/docs/current/reference/html/#repositories.query-methods.query-creation

    StarbucksRegister findByRegister(String register);

    void deleteByRegister(String register);
}
```

- In the StarbucksService.java, I removed the HashMap and altered the code.
    - Replaced delete with deleting everything from the MySQL database
    - Changed active order by adding a query to MySQL to get the active order for the current register
        - findByRegister gets the table and matches it to the register ID
    - Clear, deletes from MySQL database based on the register ID, deleting the whole row
    - Place order, creates a new entry to the table that has the current active register and the new order saved
    - Process order, checks that there is an active order

- The purpose of removing the HashMap is because the API handles multiple servers. Deleting the HashMap was important because it only works when contacting -----. 

- Included these imports to StarbucksService.java:
```sh
import com.example.springstarbucksapi.model.StarbucksRegister;
import com.example.springstarbucksapi.repository.StarbucksRegisterRepository;
```

- The changes added/removed:
- ADD:
```sh
    @Autowired private StarbucksRegisterRepository registersRepository;
```

- REMOVE:  
```sh
private HashMap<String, StarbucksOrder> orders = new HashMap<>();
```

- Under deleteAllOrders() REMOVE:
```sh
orders.clear();
```

- Under check for active drink ADD:
```sh
StarbucksOrder active = getActiveOrder(regid); 
```

- Replace orders.put(regid, new_order); with:
```sh
 StarbucksRegister register = new StarbucksRegister();
        register.setRegister(regid);
        register.setOrder(new_order);
        registersRepository.save(register);
```

- Replace return orders.get(regid); with:
```sh
  StarbucksRegister register = registersRepository.findByRegister(regid);
        if(register != null){
            return register.getOrder();
        } else {
            return null;
        }
```

- Replace orders.remove(regid); with:
```sh
    StarbucksRegister register = registersRepository.findByRegister(regid);
    register.setOrder(null);
    registersRepository.save(register);
    registersRepository.deleteById(register.getId());
```

- Replace StarbucksOrder active = orders.get(regid); with:
```sh
StarbucksOrder active = getActiveOrder(regid);
```

- Commands:
    - mvn package
    - make run
    - docker build -t cassidychu/spring-starbucks-api:2.0 .
    - docker push cassidychu/spring-starbucks-api:2.0
    - kubectl get pods
    - kubectl restart
    - kubectl exec -it jumpbox --/bin/bash
    - apt -get update
    - apt -get install mysql-client
    - mysql -u admin -p -h "ENTER YOUR PRIVATE SQL IP ADDRESS HERE" starbucks
    - show tables;

- After running the commands, the mysql table wasn't updated yet
- I had to delete the old spring-starbucks in Google Cloud and recreate the deployment.yaml file

- More Commands:
    - kubectl create -f deployment.yaml
    - export KONG="ENTER YOUR IP HERE"
    - curl $KONG/api/ping
    - curl $KONG/api/ping --header 'apikey: Zkfokey2311'
    - docker images (in starbucks-api directory)
    - docker build -t cassidychu/spring-starbucks-api:2.1 .
    - docker push cassidychu/spring-starbucks-api:2.1

- Went back into Google Cloud and deleted the deployment
- Then go back into mysql to check if the tables have been updated. Here they have been updated!
- Now go to the spring-cashier directory 
- Commands:
    - mvn package
    - make run
    - docker build -t cassidychu/spring-starbucks-api:2.0 .
    - docker push cassidychu/spring-starbucks-api:2.0


- Created a new folder called "spring-scheduler" which is meant will be used for RabbitMQ
- Added the amqp and rabbit test dependencies to starbucks-api pom.xml file:

```sh
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-amqp</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.amqp</groupId>
			<artifactId>spring-rabbit-test</artifactId>
			<scope>test</scope>
		</dependency>
```

- The deployment.yaml, ingress.yaml, and service.yaml are used to be uploaded to the Google Cloud Platform cluster. 
- Under spring-cashier, added deployment.yaml file:
```sh
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-cashier-deployment
  namespace: default
spec:
  selector:
    matchLabels:
      name: spring-cashier
  template: # create pods using pod definition in this template
    metadata:
      # unlike pod.yaml, the name is not included in the meta data as a unique name is
      # generated from the deployment name
      labels:
        name: spring-cashier
    spec:
      containers:
      - name: spring-cashier
        image: cassidychu/spring-cashier:2.2 
        env:
        - name: STARBUCKS_API_HOST
          value: "34.72.5.157"   
        - name: STARBUCKS_API_PORT
          value: "80"  
        - name: KONG_ENABLED
          value: "true"   
        - name: KONG_API_KEY
          value: "Zkfokey2311"   
        ports:
        - containerPort: 9090
          protocol: TCP
```

- ingress.yaml
```sh
apiVersion: "networking.k8s.io/v1"
kind: "Ingress"
metadata:
  name: "spring-cashier-ingress"
spec:
  defaultBackend:
    service:
      name: "spring-cashier-service"
      port:
        number: 80
```

- service.yaml
```sh
apiVersion: v1
kind: Service
metadata:
  name: spring-cashier-service 
  namespace: default
spec:
  type: ClusterIP
  ports:
  - port: 80
    protocol: TCP
    targetPort: 9090 
  selector:
    name: spring-cashier
```

# Journal Entry 14 (May 21, 2023):
[Journal Entry 14 History Part 1](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/457395518c17d22522053b2894c2cb4bab2ac4c7)

[Journal Entry 14 History Part 2](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/e6b16903f94996164be35838e7acdfa8b2de4c90)

[Journal Entry 14 History Part 3](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/02dbef0ffca9d98488fbfd12097b7de7523a00da)

[Journal Entry 14 History Part 4](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/fa0ecf9205ad8ccd1bba2a582b6802f7d897c7d5)

- I AM OFFICIALLY DONE WITH THE STARBUCKS PROJECT
- I worked on the remaining portions throughout the week but never committed
- I finished the project yesterday night around 11PM, but I am now finishing the write up
- Plan to demo today 
- Conquered the task by working on the RabbitMQ portion first and finished up the project by adding login/sign up security

## Implementing RabbitMQ 
- In the rabbitmq folder:
    - rabbitmq-console.yaml
    ```sh
    apiVersion: v1
    kind: Service
    metadata:
        name: rabbitmq-console
        namespace: default
    spec:
        type: LoadBalancer
        ports:
         - port: 80 
            targetPort: 15672
            protocol: TCP
        selector:
            name: rabbitmq
    ```

    - rabbitmq-ingress.yaml
    ```sh
    apiVersion: "networking.k8s.io/v1"
    kind: "Ingress"
    metadata:
        name: "rabbitmq-console-ingress"
    spec:
    defaultBackend:
        service:
        name: "rabbitmq-console"
        port:
            number: 80
    ```

    - rabbitmq-pod.yaml
    ```sh
    apiVersion: v1
    kind: Pod
    metadata:
    name: rabbitmq
    namespace: default
    labels:
        name: rabbitmq
    spec:
    containers:
    - name: rabbitmq
        image: rabbitmq:3-management
        imagePullPolicy: Always
        ports:
        - containerPort: 15672
        name: console
        protocol: TCP
        - containerPort: 4369
        name: nodes
        protocol: TCP
        - containerPort: 5672
        name: client
        protocol: TCP
    ```

    - rabbitmq-service.yaml
    ```sh
    apiVersion: v1
    kind: Service
    metadata:
    name: rabbitmq-service
    namespace: default
    spec:
    type: ClusterIP
    ports:
    - port: 5672 
        targetPort: 5672
        protocol: TCP
    selector:
        name: rabbitmq
    ```

- In the spring-scheduler folder, I created two different folders. One is for the models and the other is for repository. I just copied over all the files in both folders from the starbucks-api folder and pasted them in spring-sceduler. The difference between spring-schduler and starbucks-api is that spring-schduler has a PickupOrder.java whereas starbucks-api has a StarbucksApiConfig.java file. 

## Spring Scheduler

- application-dev.properties
```sh
logging.level.org=INFO
spring.rabbitmq.host=${RABTMQ_HOST:localhost}
spring.rabbitmq.port=${RABTMQ_PORT:5672}
spring.rabbitmq.username=${RABTMQ_USER:guest}
spring.rabbitmq.password=${RABTMQ_PASSWORD:guest}

spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:starbucks
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

server.port=9000
```

- application-prod.properties
```sh
logging.level.org=INFO
spring.rabbitmq.host=${RABTMQ_HOST:localhost}
spring.rabbitmq.port=${RABTMQ_PORT:5672}
spring.rabbitmq.username=${RABTMQ_USER:guest}
spring.rabbitmq.password=${RABTMQ_PASSWORD:guest}


spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/starbucks
spring.datasource.username=${MYSQL_USER:root}
spring.datasource.password=${MYSQL_PASSWORD:cmpe172}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

- application.properties
```sh
logging.level.org=INFO
spring.rabbitmq.host=${RABTMQ_HOST:localhost}
spring.rabbitmq.port=${RABTMQ_PORT:5672}
spring.rabbitmq.username=${RABTMQ_USER:guest}
spring.rabbitmq.password=${RABTMQ_PASSWORD:guest}

spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:starbucks
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

server.port=9000
```
- background-worker-pod.yaml
```sh
apiVersion: v1
kind: Pod
metadata:
  name: background-worker
  namespace: default
spec:
  containers:
  - name: background-worker
    image: cassidychu/background-worker:1.0
    env:
    - name: RABTMQ_HOST
      value: "10.8.14.141"   
    - name: RABTMQ_PORT
      value: "5672"  
    - name: RABTMQ_USER
      value: "guest"   
    - name: RABTMQ_PASSWORD
      value: "guest"
    - name: MYSQL_HOST
      value: "10.11.112.3"   
    - name: MYSQL_USER
      value: "admin"  
    - name: MYSQL_PASSWORD
      value: "cmpe172"       
    imagePullPolicy: Always
    ports:
    - containerPort: 8080
      protocol: TCP
```

- background-worker-service.yaml
```sh
apiVersion: v1
kind: Service
metadata:
  name: background-worker-service
  namespace: default
spec:
  type: ClusterIP
  ports:
  - port: 80 
    targetPort: 8080
    protocol: TCP
  selector:
    name: background-worker
```

- Dockerfile
```sh
FROM openjdk:11
EXPOSE 8080
ADD ./target/spring-scheduler-1.0.jar /srv/spring-scheduler-1.0.jar
CMD java -jar /srv/spring-scheduler-1.0.jar  --spring.profiles.active=prod
```

**In the pom.xml, don't forget change the version to 1.0**

- Create a new file called PickupOrder.java

- PickupOrder.java
```sh
/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springscheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.springscheduler.repository.StarbucksDrinkRepository;
import com.example.springscheduler.model.StarbucksDrink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

// Imports for HTTP
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.lang.InterruptedException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Component
@RabbitListener(queues = "drinks")
public class PickupOrder {
	
	private static final Logger log = LoggerFactory.getLogger(PickupOrder.class);

	@Autowired
	private StarbucksDrinkRepository drinkRepository;

	@RabbitHandler
	public void pickUpMakeDrink(String drinkId) {
		long id = Long.parseLong(drinkId);
		List<StarbucksDrink> drinks = drinkRepository.findById(id);
		if(drinks.isEmpty()) return;

		//Set drink status to in progress
		StarbucksDrink drink = drinks.get(0);
		drink.setStatus("Your drink is being made!");
		drinkRepository.save(drink);
		log.info("Picked up drink: " + drinkId);

		
		try{
			Thread.sleep(20_000);
		} catch (Exception error){

		}
		
		//Set drink status compelete
		drink.setStatus("Your drink is ready!");
		drinkRepository.save(drink);
		log.info("Drink Completed: " + drinkId);
	}


}
```

- PickupOrder.java is meant to save the drink when it has been placed and notify the customer that their drink is being made. It waits 20 seconds before announcing that the drink is ready and completed. 

**Changes in starbucks-api**
- Make sure in the pom.xml file in starbucks-api, you use version 2.6.4 or else an error will occur. 

- Set a new StarbucksDrink.java and StarbucksDrinkRepository.java in both starbucks-api and spring-scheduler.

- StarbucksDrink.java
```sh
package com.example.springstarbucksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/* 
    https://www.baeldung.com/intro-to-project-lombok
    https://www.baeldung.com/lombok-ide
            
    Spring JPA References:

    https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html
    https://docs.spring.io/spring-data/jpa/docs/2.5.1/api
    https://www.baeldung.com/spring-data-rest-relationships
    https://www.baeldung.com/hibernate-one-to-many
    https://www.baeldung.com/jpa-one-to-one
    https://www.baeldung.com/jpa-cascade-types
    https://www.baeldung.com/category/persistence/tag/jpa

*/

@Entity
@Table(name = "STARBUCKS_DRINK")
@Data
@RequiredArgsConstructor
public class StarbucksDrink {

    private @Id
    @GeneratedValue
    Long id;
    private String status;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private StarbucksOrder order;


}
```

- StarbucksDrinkRepository.java
```sh
package com.example.springstarbucksapi.repository;

/* https://docs.spring.io/spring-data/jpa/docs/2.4.6/reference/html/#repositories */

import com.example.springstarbucksapi.model.StarbucksDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StarbucksDrinkRepository extends JpaRepository<StarbucksDrink, Long> {

    StarbucksDrink findOneById(long id);
    List<StarbucksDrink> findById(long id);

}
```

- Doing so creates a relationship to the order and sets the status on the drink
- RabbitMQ will get the queue and send the drink in query 

**StarbucksOrderController.java:**
- Add this import
```sh
import com.example.springstarbucksapi.model.StarbucksDrink;
```

- 
```sh
@GetMapping("/order/drinks")
    List<StarbucksDrink> setDrinkInProgress() {
        return service.getDrinks();
    }

    @GetMapping("/order/drink/inprogress/{drinkId}")
    StarbucksDrink setDrinkInProgress(@PathVariable long drinkId) {
        return service.setDrinkStatus(drinkId, "In Progress") ;
    }

    @GetMapping("/order/drink/fulfilled/{drinkId}")
    StarbucksDrink setDrinkFulfilled(@PathVariable long drinkId) {
        return service.setDrinkStatus(drinkId, "Fulfilled") ;
    }
```
- Create a StarbucksApiConfig.java file
```sh
package com.example.springstarbucksapi;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StarbucksApiConfig {

    @Bean
    public Queue drinks() {
        return new Queue("drinks");
    }
}
```
![get mapping drinks](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/get-mapping-drinks.png)

- application-dev.properties
```sh
# H2 DB Console: http://localhost:8080/h2-console
# REF:  https://www.baeldung.com/spring-boot-h2-database 
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:starbucks
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

logging.level.org=INFO
spring.rabbitmq.host=${RABTMQ_HOST:localhost}
spring.rabbitmq.port=${RABTMQ_PORT:5672}
spring.rabbitmq.username=${RABTMQ_USER:guest}
spring.rabbitmq.password=${RABTMQ_PASSWORD:guest}

server.port=8000
# REF:  https://www.baeldung.com/spring-profiles
```

- application-prod.properties and application-test.properties
```sh
# MYSQL 
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/starbucks
spring.datasource.username=${MYSQL_USER:root}
spring.datasource.password=${MYSQL_PASSWORD:cmpe172}

logging.level.org=ERROR
spring.rabbitmq.host=${RABTMQ_HOST:localhost}
spring.rabbitmq.port=${RABTMQ_PORT:5672}
spring.rabbitmq.username=${RABTMQ_USER:guest}
spring.rabbitmq.password=${RABTMQ_PASSWORD:guest}
# REF:  https://www.baeldung.com/spring-profiles
```

- deployment.yaml
```sh
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-starbucks-api-deployment
  namespace: default
spec:
  selector:
    matchLabels:
      name: spring-starbucks-api
  replicas: 4 # tells deployment to run 4 pods matching the template
  template: # create pods using pod definition in this template
    metadata:
      # unlike pod.yaml, the name is not included in the meta data as a unique name is
      # generated from the deployment name
      labels:
        name: spring-starbucks-api
    spec:
      containers:
      - name: spring-starbucks-api
        image: cassidychu/spring-starbucks-api:3.0
        env:
        - name: MYSQL_HOST
          value: "10.11.112.3"   
        - name: MYSQL_USER
          value: "admin"  
        - name: MYSQL_PASSWORD
          value: "cmpe172"
        - name: RABTMQ_HOST
          value: "10.8.14.141"   
        - name: RABTMQ_PORT
          value: "5672"  
        - name: RABTMQ_USER
          value: "guest"   
        - name: RABTMQ_PASSWORD
          value: "guest"   
        ports:
        - containerPort: 8080
```

**In Spring Scheduler**
    - Commands:
        - kubectl create -f background-worker-service.yaml
        - kubectl create -f background-worker-pod.yaml
    
**Postman Test**
- Post New Order

![new order test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/new-order-test.png)

- Post Pay for Order

![pay for order test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/pay-for-order-test.png)

- Get Drinks Copy

![get drinks copy test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/get-drinks-copy-test.png)

- In Docker, create a new database for users:
    - Commands for mysql:
        - mysql --password
            - (enter password)
        - show databases;
        - use starbucks;
        - show tables;
        - create database users;
        - use users
        - show tbales; 

![create user table part one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/create-user-table-part-one.png)

![create user table part two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/create-user-table-part-two.png)

**Spring-cashier**
- Command:
    - mvn package
    - mvn spring-boot:run
    - docker build -t cassidychu/spring-cashier:3.3
    - docker push cassidychu/spring-cashier:3.3
    - kubectl create -f deployment.yaml

- I had to rebuild the docker image multiple times, delete from GCP and then do it all over again

![drinks in rabbitmq](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/drinks-in-rabbitmq.png)

## Google Cloud Platform Cluster, Workload, Ingress/Service
![gcp final cluster](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/gcp-final-cluster.png)

![gcp final workloads](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/gcp-final-workloads.png)

![gcp final ingress service](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/gcp-final-ingress-service.png)


## Support Admin Logins for Starbucks Employees
- 1. Copied what was needed for Login/Signup from Professor Nguyen's gitpod under his spring-gumball-v3.5 folder
- 2. I watched/found the Starbucks Client + Spring Gumball v3.5 on GKE Demo to be extremely helpful in implementing admin login

- In the spring-cashier folder, in the src, add 2 new folders called config and login.
- In the config folder add:
    - CustomerLoginSuccessHandler.java
    ```sh
    package com.example.springcashier.config;

    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.web.DefaultRedirectStrategy;
    import org.springframework.security.web.RedirectStrategy;
    import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;

    @Configuration
    public class CustomLoginSucessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Override
        protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                throws IOException {
            String targetUrl = determineTargetUrl(authentication);
            if(response.isCommitted()) return;
            RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }

        protected String determineTargetUrl(Authentication authentication){
            String url = "/login?error=true";
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<String>();
            for(GrantedAuthority a : authorities){
                roles.add(a.getAuthority());
            }
            if(roles.contains("ADMIN")){
                url = "/admin/dashboard";
            }else {
                url = "/user/cashier";
            }
            return url;
        }
    }
    ```

    -  WebMvcConfig.java
    ```sh
    package com.example.springcashier.config;

    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



    @Configuration
    public class WebMvcConfig implements WebMvcConfigurer {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/access-denied").setViewName("access-denied");
            registry.addViewController("/about").setViewName("about-us");
        }
    }
    ```

    - WebSecurityConfig.java
    ```sh
    package com.example.springcashier.config;

    import com.example.springcashier.login.UserServiceImpl;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

    @Configuration
    @EnableWebSecurity
    public class WebSecurityConfig {

        @Autowired
        private CustomLoginSucessHandler sucessHandler;

        @Bean
        public UserDetailsService userDetailsService() {
            return new UserServiceImpl();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
            return authConfig.getAuthenticationManager();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

            authProvider.setUserDetailsService(userDetailsService());
            authProvider.setPasswordEncoder(passwordEncoder());

            return authProvider;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                    // URL matching for accessibility
                    .antMatchers("/", "/about", "/auth/login.css", "/login", "/register", "/h2-console/**").permitAll()
                    .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                    .antMatchers("/user/**").hasAnyAuthority("USER")
                    .anyRequest().authenticated()
                    .and()
                    // form login
                    .csrf().disable().formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .successHandler(sucessHandler)
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .and()
                    // logout
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access-denied");

            http.authenticationProvider(authenticationProvider());
            http.headers().frameOptions().sameOrigin();

            return http.build();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
        }

    }
    ```

- In the login folder add:
    - AdminController.java
    ```sh
    package com.example.springcashier.login;


    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;

    @Controller
    public class AdminController {

        @RequestMapping(value = {"/admin/dashboard"}, method = RequestMethod.GET)
        public String adminHome(){
            return "admin/dashboard";
        }
    }
    ```

    - AuthController.java
    ```sh
    package com.example.springcashier.login;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;

    import javax.validation.Valid;
    import java.util.List;

    @Controller
    public class AuthController {
        @Autowired
        UserService userService;

        @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
        public String login(Model model, @Valid User user, BindingResult bindingResult){
            return "auth/login";
        }

        @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
        public String register(Model model){
            model.addAttribute("user", new User());
            return "auth/login";
        }

        @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
        public String registerUser(Model model, @Valid User user, BindingResult bindingResult){

            if(bindingResult.hasErrors()){
                System.out.println( "User registration failed!" ) ;
                model.addAttribute("successMessage", "User registration failed!");
                model.addAttribute("bindingResult", bindingResult);
                return "redirect:" + "register?regfailed=true";
            }

            List<Object> userPresentObj = userService.isUserPresent(user);
            if((Boolean) userPresentObj.get(0)){
                System.out.println( "User already registered!" ) ;
                model.addAttribute("successMessage", userPresentObj.get(1));
                return "redirect:" + "register?userexists=true";
            }

            user.setRole(Role.USER);
            userService.saveUser(user);
            model.addAttribute("successMessage", "User registered successfully!");

            return "redirect:" + "login";
        }
    }
    ```

    - Role.java
    ```sh
    package com.example.springcashier.login;

    public enum Role {
        USER("User"),
        ADMIN("Admin");

        private final String value;

        private Role(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    ```

    - User.java
    ```sh
    package com.example.springcashier.login;

    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;
    import org.hibernate.validator.constraints.Length;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import javax.persistence.*;
    import javax.validation.constraints.Email;
    import javax.validation.constraints.NotNull;
    import java.time.LocalDateTime;
    import java.util.Collection;
    import java.util.Collections;

    import lombok.Data;
    import lombok.RequiredArgsConstructor;


    @Entity
    @Data
    @Table(name = "users")
    public class User implements UserDetails  {
        @SequenceGenerator(
                name = "users_sequence",
                sequenceName = "users_sequence",
                allocationSize = 1
        )
        @Id
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "users_sequence"
        )
        private int id;

        @NotNull(message = "Email cannot be empty")
        @Email(message = "Please enter a valid email address")
        @Column(name = "email", unique = true)
        private String email;

        @NotNull(message = "Password cannot be empty")
        @Length(min = 7, message = "Password should be atleast 7 characters long")
        @Column(name = "password")
        private String password;

        @CreationTimestamp
        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @UpdateTimestamp
        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        @Enumerated(EnumType.STRING)
        private Role role;

        @Column(name = "locked")
        private Boolean locked = false;

        @Column(name = "enabled")
        private Boolean enabled = true;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
            return Collections.singletonList(authority);
        }

        @Override
        public String getUsername() {
            return email;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return !locked;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return enabled;
        }

    
    }
    ```

    - UserController.java
    ```sh
    package com.example.springcashier.login;

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;

    @Controller
    public class UserController {

        @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
        public String homePage(){
            return "user/dashboard";
        }
    }
    ```

    - UserRepository.java
    ```sh
    package com.example.springcashier.login;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;

    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);
    }
    ```

    - UserService.java
    ```sh
    package com.example.springcashier.login;

    import java.util.List;

    public interface UserService {
        public void saveUser(User user);
        public List<Object> isUserPresent(User user);
    }
    ```

    - UserServiceImpl.java
    ```sh
    package com.example.springcashier.login;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.Arrays;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class UserServiceImpl implements UserService, UserDetailsService {

        @Autowired
        BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        UserRepository userRepository;

        @Override
        public void saveUser(User user) {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            //user.setRole(Role.USER);
            userRepository.save(user);
        }

        @Override
        public List<Object> isUserPresent(User user) {
            boolean userExists = false;
            String message = null;
            Optional<User> existingUserEmail = userRepository.findByEmail(user.getEmail());
            if(existingUserEmail.isPresent()){
                userExists = true;
                message = "Email Already Present!";
            }
            System.out.println("existingUserEmail.isPresent() - "+existingUserEmail.isPresent());
            return Arrays.asList(userExists, message);
        }

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return userRepository.findByEmail(email).orElseThrow(
                    ()-> new UsernameNotFoundException(
                            String.format("USER_NOT_FOUND", email)
                    ));
        }
    }
    ```
- In the main folder, add a HomeController.java file:
```sh
package com.example.springcashier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getAction(Model model) {
        return "redirect:" + "user/cashier";
    }

}
```

- Some minor changes that were made in SpringCashierController.java was ensuring @RequestMapping led to "/user/cashier" instead of just "/". For any returns in the java file I added user/starbucks so that the user can be redirected to the home page.

- In resources add the login.css
```sh
/* https://codepen.io/khadkamhn/pen/ZGvPLo */

body{
    margin:0;
    color:#6a6f8c;
    background:#c8c8c8;
    font:600 16px/18px 'Open Sans',sans-serif;
}
*,:after,:before{box-sizing:border-box}
.clearfix:after,.clearfix:before{content:'';display:table}
.clearfix:after{clear:both;display:block}
a{color:inherit;text-decoration:none}

.login-error {
  padding: 10px 0;
  border: 1px solid red;
  text-align: center;
}

.login-wrap{
    width:100%;
    margin:auto;
    max-width:525px;
    min-height:670px;
    position:relative;
    box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
}
.login-html{
    width:100%;
    height:100%;
    position:absolute;
    padding:90px 70px 50px 70px;
    background:rgba(40,57,101,.9);
}
.login-html .sign-in-htm,
.login-html .sign-up-htm{
    top:0;
    left:0;
    right:0;
    bottom:0;
    position:absolute;
    transform:rotateY(180deg);
    backface-visibility:hidden;
    transition:all .4s linear;
}
.login-html .sign-in,
.login-html .sign-up,
.login-form .group .check{
    display:none;
}
.login-html .tab,
.login-form .group .label,
.login-form .group .button{
    text-transform:uppercase;
}
.login-html .tab{
    font-size:22px;
    margin-right:15px;
    padding-bottom:5px;
    margin:0 15px 10px 0;
    display:inline-block;
    border-bottom:2px solid transparent;
}
.login-html .sign-in:checked + .tab,
.login-html .sign-up:checked + .tab{
    color:#fff;
    border-color:#1161ee;
}
.login-form{
    min-height:345px;
    position:relative;
    perspective:1000px;
    transform-style:preserve-3d;
}
.login-form .group{
    margin-bottom:15px;
}
.login-form .group .label,
.login-form .group .input,
.login-form .group .button{
    width:100%;
    color:#fff;
    display:block;
}
.login-form .group .input,
.login-form .group .button{
    border:none;
    padding:15px 20px;
    border-radius:25px;
    background:rgba(255,255,255,.1);
}
.login-form .group input[data-type="password"]{
    -webkit-text-security:circle;
}
.login-form .group .label{
    color:#aaa;
    font-size:12px;
}
.login-form .group .button{
    background:#1161ee;
}
.login-form .group label .icon{
    width:15px;
    height:15px;
    border-radius:2px;
    position:relative;
    display:inline-block;
    background:rgba(255,255,255,.1);
}
.login-form .group label .icon:before,
.login-form .group label .icon:after{
    content:'';
    width:10px;
    height:2px;
    background:#fff;
    position:absolute;
    transition:all .2s ease-in-out 0s;
}
.login-form .group label .icon:before{
    left:3px;
    width:5px;
    bottom:6px;
    transform:scale(0) rotate(0);
}
.login-form .group label .icon:after{
    top:6px;
    right:0;
    transform:scale(0) rotate(0);
}
.login-form .group .check:checked + label{
    color:#fff;
}
.login-form .group .check:checked + label .icon{
    background:#1161ee;
}
.login-form .group .check:checked + label .icon:before{
    transform:scale(1) rotate(45deg);
}
.login-form .group .check:checked + label .icon:after{
    transform:scale(1) rotate(-45deg);
}
.login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
    transform:rotate(0);
}
.login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
    transform:rotate(0);
}

.hr{
    height:2px;
    margin:60px 0 50px 0;
    background:rgba(255,255,255,.2);
}
.foot-lnk{
    text-align:center;
}
```

- In templetes add:
    - access-denied.html
    ```sh
    <!--access-denied.html-->
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Access Denied</title>
    </head>
    <body>
    <h2>Access Denied</h2>
    </body>
    </html> 
    ```

    - homepage.html
    ```sh
    <!--homepage.html-->
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <title>Homepage</title>
    </head>
    <body>
    <h2>Welcome to Homepage</h2>
    <p sec:authorize="hasRole('ROLE_ANONYMOUS')">Text visible to anonymous.</p>
    <p sec:authorize="hasRole('USER')">Text visible to user.</p>
    <p sec:authorize="hasRole('ADMIN')">Text visible to admin.</p>
    <p sec:authorize="isAuthenticated()">Text visible only to authenticated users.</p>

    <div sec:authorize="hasRole('ROLE_ANONYMOUS')">
        <p><a th:href="@{|/login|}" th:text="'Log in'"></a></p>
        <p><a th:href="@{|/register|}" th:text="'Register'"></a></p>
    </div>

    <div sec:authorize="isAuthenticated()">
        <p>Logged as: <span sec:authentication="name"></span></p>
        <p>Has role: <span sec:authentication="authorities"></span></p>
        <p sec:authorize="hasAuthority('USER')"><a th:href="@{|/dashboard|}" th:text="'User Dashboard'"></a></p>
        <p sec:authorize="hasAuthority('ADMIN')"><a th:href="@{|/admin/dashboard|}" th:text="'Admin Dashboard'"></a></p>
        <a th:href="@{/logout}">Log out</a>
    </div>

    </body>
    </html>
    ```
![final login](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/final-login.png)

![final signup](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/final-setup.png)

- When you're logged in you can go to "/dashboard" and have the option to either log out or go to home.

![logout or home](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/logout-or-home.png)

## Health Check
- Edit the load balancer on GCP in Computer Engine
- Go to Health Check and edit as "/about" under request path

![health check fix](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/health-check-fix.png)

![spring cashier ingress](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/spring-cashier-ingress.png)

- I was getting a WhiteLabel error from deployment after. I was able to fix it by adding starbucks.html to the user folder. 
- I made sure to make 2 pods for the spring-cashier
- Another error I ran into was being stuck on the login page while running multiple pods.
- I fixed the error by adding always to the application.properties and reimplemented sessions because I deleted it earlier due to a different error that arose. 

- In the application.properties folder make these changes:
```sh
server.port=9090

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/users
spring.datasource.username=${MYSQL_USERNAME:root}
spring.datasource.password=${MYSQL_PASSWORD:cmpe172}

starbucks.api.host=${STARBUCKS_API_HOST:34.72.5.157}
starbucks.api.port=${STARBUCKS_API_PORT:80}
kong.enabled=${KONG_ENABLED:true}
kong.api.key=${KONG_API_KEY:Zkfokey2311}

spring.session.store-type=jdbc
#https://stackoverflow.com/questions/53823174/how-to-initialize-schema-in-spring-session-with-jdbc
spring.session.jdbc.initialize-schema=always
```

# Journal Entry 15 (May 22, 2023):
[Journal Entry 15 History Part 1](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/a2fc3b2ddbea103097417cc9b9453465a421f2ea)

[Journal Entry 15 History Part 2](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/7a3ef857a2547df7c1a997241a65e112a70491ab)

[Journal Entry 15 History Part 3](https://github.com/nguyensjsu/cmpe172-cassidychu5/commit/41a88a268b6067c6f3b58fe3e76b6f72c933a513)

- Made the thread sleep from 20 seconds to 60 seconds because while I filmed for the demo, 20 seconds was not enought time to place 2 orders and show it in RabbitMQ.
- I made sure to update all of the versions so it can work properly.
- I finished filming the demo

## Demo Run Through with everything required:
**Setup Steps:**
1. Show the Starbucks API "Kong API Gateway" endpoint and API being used.  Demo this via a CURL ping test to API.
    - Commands:
        - curl "ENTER YOUR KONG PROXY IP ADDRESS HERE"/api/ping (without apikey)
        - curl "ENTER YOUR KONG PROXY IP ADDRESS HERE"/api/ping --header 'apikey: "ENTER YOUR API KEY HERE"' (with apikey)            

![demo curl test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-curl-test.png)

![demo matches api](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-matches-api.png)

![demo matches kong proxy](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-matches-kong-proxy.png)

2. Show the launch manifest / configuration for Starbucks Cash Register confirming connection to API via Kong with API Key

![demo matches manifest](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-matches-manifest.png)

3. Start up and Log into your Starbucks Cash Register App (your ported Spring MVC version)

![demo first login](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-first-login.png)

4. Show the launch command to run Starbucks "Mobile" App with connection to the same Kong API Gateway and API Key
    - As you can see it uses the same IP address and API key from Kong under the make starbucks-app-run command

![demo app launch manifest](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-app-launch-manifest.png)

5. Start up the "Starbucks Mobile App" pointing out the "Store/Register" ID being used
    - Command:
        - make starbucks-app-run
    - To create a new card go to the main folder and insert these commands:
        - login (bypass the pin)
        
![demo running app](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-running-app.png)

- The register ID of 5012349 matches Dub-C and the one being used for the starbucks mobile app

![demo store register id](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-store-register-id.png)

6. Connect to Backend MySQL Database and query the "New Starbucks Card" created by Starbucks Mobile App
    - Commands:
        - kubectl exec -it jumpbox -- /bin/bash
        - apt-get update
        - apt-get install mysql-client -y 
        - mysql -u admin -p -h <ENTER SQL PRIVATE IP HERE> starbucks
        - show tables;
        - select * from starbucks_card;

![demo start jumpbox](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-start-jumpbox.png)

![demo mysql jumpbox](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-mysql-jumpbox.png)

![demo login mysql](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-login-mysql.png)

![demo show tables](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-show-tables.png)

![demo query new card](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-query-new-card.png)


7. Query to show the Card Number, Card Code and starting Balance in "Activated Status"

![demo query new card](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-query-new-card.png)

**Demo Steps:**
1. Start the Demo by accessing endpoint to Load Balancer (Ingress) for Cashier's App. Make sure to prove that the IP matches that in your GKE Cluster.
    - The IP address from the cashier matches the spring-cashier-ingress IP address.

![demo endpoint](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-endpoint.png)

![demo ingress endpoint](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-ingress-endpoint.png)


2. Create a new User Account and then Log into our Cashier's App and then Log out.

- I logged out of the first account I logged in with.

![demo logging out of first](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-logging-out-of-first.png)

- Creating a new account.

![demo sign up](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-sign-up.png)

3. Log into Cashier's App again (2nd time). Then, create a new Order for a specific Store / Register (matching the Register used by Starbucks Mobile App)

![demo sign in](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-sign-in.png)

![demo place order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-place-order.png)

4. Query the MySQL DB to show the Order matching that displayed on Cashier's App
    - Command:
        - select * from starbucks_order;

- The drinks and information match.

![demo place order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-place-order.png)

![demo starbucks order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-starbucks-order.png)


5. From the "Starbucks Mobile App", pay for the Order and point out the remaining "Card Balance" after purchase
    - Commands to pay:
        - touch (3,3) - shows card
        - touch (2,2) - pays
        - touch (3,3) - gets card and shows balance
        
![demo pay](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-pay.png)

![demo balance](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-balance.png)

6. Query the MySQL DB to show the status of the Order and Card balance. 
    - Command:
        - select * from starbucks_card;

- The order has been paid for.

![demo paid](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-paid.png)

7. Refresh the Order Page on "Cashier's App" to confirm the Order Status (matching that shown in MySQL DB)

- Shows that the order was paid with the same card number we used and shows the remaining balance.

![demo get](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-get.png)

8. Clear the Order from the Cashier's App and Place a new Order for Payment and repeat the Payment steps with Starbucks Mobile App
    - The order IDs may be a bit different because I had to redo this a bunch of times to get both orders to appear in the queue. I originally had 20 seconds for the thread to sleep but then changed it to 60 so it would give more time. 

![demo clear](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-clear.png)

9. Query the MySQL DB to show the status of the Order and Card balance. 


10. Query RabbitMQ to show the Order in the Queue awaiting Order Processing (i.e. Drink to be Made)

![demo rabbitmq two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-rabbitmq-two.png)

11. Show the GKE Pod Logs of your Order Processing Worker that picked up the Order and made the Drink

![demo log progress](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-log-progress.png)

- One of the orders has finished, now there's one more.

![demo rabbitmq one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-rabbitmq-one.png)

![demo log process](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-log-process.png)

![demo log complete](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-log-complete.png)

12. Using CURL or Postman, make an API call the check the status of the Order (i.e. to see if the Drink is Available Yet)

- Under GET Drink Copy, after calling the API you can see that both drinks are there which have the ID of 56 & 59.

![demo postman](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-postman.png)

13. Once the Order has been "Fulfilled", Query the MySQL DB to show the final status of All Paid and Fulfilled Orders
    - Command:
        - select * from starbucks_drink as a, starbucks_order as b WHERE a.order_id = b.id AND a.status = "Your drink is ready!";

![demo done](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-done.png)

# Scoring
## Cashier's App (25 points)
- Port Node.js App to Spring MVC (required)
    - Web rendering must be done in View Templates
        - **I used Thymeleaf templates**
    ![starbucks html](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-html.png)

    ![options html](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/options-html.png)

    - Controller must process JSON responses from API and pass to View via Models
        - **Jackson is being used. Here is an example of how the objectMapper is converting the JSON response string to an Order class which can be used later on throughout the multiple functions used in the SpringCashierController.java file**

    ![import jackson](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/import-jackson.png)

    ![json node](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/json-node.png)

    ![object mapper](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/object-mapper.png)

    - Output and "Look and Feel" of Web UI must match that of Node.js App

    ![demo place order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-place-order.png)

    - Implementation must not just use Rest Client example code (from instructor) as-is


### Support Admin Logins for Starbucks Employees (5 points)
- Must not store credentials in memory or hard code in source code
    - Commands in MySQL:
        - show tables;
        - select * from users;
    - Users are stored in the database, below are all of the accounts I have made for the cashier website. 
    
    ![user registration](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/user-registration.png)

    ![sql users](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/sql-users.png)

    ![sql select users](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/sql-select-users.png)

    - Should also include New Account Registration and Logout

    **Sign In**
    ![demo sign in](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-sign-in.png)

    **Sign Up**
    ![demo sign up](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-sign-up.png)

### - Support In Store Order Processing (20 points -- See Diagram Below)
- See "Demo the following in your Video submission" below
## - Scalable Cloud Deployment on GCP  (25 points)
### - External Load Balancer as Ingress for Cashier's App (10 points)

- Upon clicking onto the spring-cashier-ingress IP address, it will lead you to the cashier app. 

![external lb](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/external-lb.png)

![cashier external](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/cashier-external.png)

### - Internal Load Balancer for Starbucks API behind Kong API Gateway (15 points)

- All requests to the starbucks API go through the Kong API Gateway. These requests are directed to the starbucks-api-service which acts as an internal load balancer for the starbucks api. 

![starb api internal](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starb-api-internal.png)

## Implementation Uses Required Cloud Databases (25 points)
### - MySQL Database 8.0 (15 points)**
    - Must use Cloud SQL (MySQL Option)

![cloud sql](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/cloud-sql.png)

    - Update Starbucks API to use JPA with MySQL

- Created models for StarbucksCard.java StarbucksDrink.java, StarbucksOrder.java, and StarbucksRegister.java.

![starbucks card](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-card.png)

![starbucks drink](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-drink.png)

![starbucks order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-order.png)

![starbucks register](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-register.png)

- Created repositories for StarbucksCardRepository.java, StarbucksDrinkRepository.java, StarbucksOrderRepository.java, and StarbucksRegisterRepository.java.

![starbucks card repository](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-card-repository.png)

![starbucks drink repository](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-drink-repository.png)

![starbucks order repository](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-order-repository.png)

![starbucks register repository](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/starbucks-register-repository.png)

### - RabbitMQ (10 points)
    - Must use GKE RabbitMQ Operator

![drink queue](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/drink-queue.png)

![rabbit console](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/rabbit-console.png)

    - Extend the Starbucks API to support async order processing (to use RabbitMQ)

![api process push order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/api-process-push-order.png)

## Starbucks API for Mobile App and Store Front (25 points)
### - Deployed with Kong API Gateway with API Key Authentication (10 points)

![demo curl test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-curl-test.png)

![demo matches api](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-matches-api.png)

![demo matches kong proxy](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-matches-kong-proxy.png)

### - Implement RabbitMQ Check Order Status for "Drinks Available" (15 Points)
    - Async Request API to "Make the Drink" once Order has been Paid (i.e. put request into a Queue)

![api process push order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/api-process-push-order.png)

![demo rabbitmq two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/demo-rabbitmq-two.png)

    - Async Check Order Status API to "Check Status of Drink" in the Starbucks Database

![get drinks copy test](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/get-drinks-copy-test.png)

![get drinks starbucks service](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/get-drinks-starbucks-service.png)

    - Will need a Background Worker Job (i.e. Spring Scheduler) to pick up Orders and Make Drinks

![pick up order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/pick-up-order.png)

    - Background Worker should be a "Single Resilient POD" which auto restarts on crashes

![worker pod](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/worker-pod.png)

# ASTAH Diagram
![astah](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/project/screenshots/astah.png)

- My solution scales, by increasing the number of pods for the Starbucks API and Cashier app, and by scaling the compute power allocated to my kubernetes cluster. As more request are made to either the API or cashier app, new pods can be spun up to match the demand. If so many pods got spun up that the cluster has no more resources, more resources can easily be allocated to the cluster. The main bottleneck to my system is the CloudSQL database. The only way to scale the database would be to horizontally scale, adding new instance of the databases, but the current architure does not easily allow for that. 

- My solution can handle > 1 million mobile devices. The one thing in this architecture that does not scale very well is the CloudSQL database, however it can handle a large number of queries in its current state. With the knowledge that all the queries that are being made are realatively simple (no complex joins) I belive the database can handle 1 million devices. Because I belive the least scalable piece of my architecure can handle 1 million devices, I belive the architecture as a whole can do so as well.