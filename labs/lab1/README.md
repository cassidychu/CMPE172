# CMPE 172 - Lab #1 Notes

For this Lab, used these to Configure my Project:
- **Project: Maven Project**
- **Language: Java Language (JDK 11)**
- **Spring Boot Version: (2.7.8)**

## The /labs/lab1 folder includes:
- demo-initializr
- demo-intellij
- demo-docker
- images (screenshots)
- README.md (lab notes)

## Spring Demo App Using Initializr
**Professors Instructions:**
1. Generate a New Spring Boot Project using the following parameters via https://start.spring.io/ Links to an external site.(online Spring Boot Initializr).

- Group: com.example
- Artifact: demo-initializr
- Name: demo-initializr
- Package Name: come.example.demo-initializr
- Packaging: Jar
- Dependencies:
    - Spring Web

2. Extract the Zip file and store in your Git Folder /labs/lab1/demo-initializr. Make changes to the App using the following code. Note: you will need to modify the Class Name to match the code generated.

```sh
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoXXXApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoXXXApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
```

3. Run the Spring Demo App on your Local Machine with the message: Hello CMPE 172!. Take a "Screenshot" of your Browser and Full Desktop and include this in your Lab Notes README.md in GitHub.

Commands: 
- mvn package - Compiles project
- mvn spring-boot:run - runs project
![hello cmpe172](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab1/screenshots/hello-cmpe172.png)

## Spring Demo App Configured for Docker and Kubernettes
(did not use Kubernettes for this lab, it was removed from the rubric)
**Professors Instructions:**
1. Generate a New Spring Boot Project using the following parameters from Visual Studio Code or using Spring Boot Initializr. Store in your Git Folder /labs/lab1/demo-docker.

- Group: com.example
- Artifact: demo-docker
- Name: demo-vscode
- Package Name: come.example.demo-docker
- Packaging: Jar
- Dependencies:
    - Spring Web

2. Make the Appropriate Code Changes using the following:
```sh
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoXXXApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoXXXApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
```

3. Using the Lab files provided, Do the following:

- Build Docker Image
- Run Docker Container in Docker Desktop (Locally)
- Run Docker Container in Google Cloud Kubernetes Engine (not included in lab1)

Files (add to demo-docker project):

- Dockerfile
- Makefile
- pod.yaml
- service.yaml (not included in lab1)

Take "Screenshots" of your Browser and Full Desktop and include this in your Lab Notes README.md in GitHub. Screenshots should include: Spring Boot App running from Docker Desktop and Google Cloud.

Follow the instructions from Demos in class.

Commands:
- mvn compile - Compiles project
- mvn package - Creates jar file (demo-docker-1.0.jar)
- java -jar target/demo-docker-1.0.jar - runs project on port 80
- make docker-build - builds an image for docker1
- docker build -t demo-docker
- docker ps - checks that it contains the correct container ID
- docker exec -it demo-docker1 bash - shows the root number and host thats in docker container ID
    - ls - lists files in another environment 
    - cd srv - folder
    - ls - shows list
    - ls -l - shows demo-docker-1.0.jar file
    - exit - exit
- docker logs demo-docker1 - shows all of the logs done in demo-docker1
- docker run --name demo-docker2 -td -p 82:8080 demo-docker - creates - docker image 2
- docker rm demo-docker2 - removes demo-docker2 image due to the conflicting container name
- docker run --name demo-docker2 -td -p 82:8080 demo-docker - shows hello world in port 82 now + will show up in Docker
- docker ps - check if both demo-docker1 and demo-docker2's container 


![demo-docker1-image](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab1/screenshots/demo-docker1-cmpe172.png)

I added an extra screenshot of following the demo video where docker2 image was created.

![demo-docker2-image](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab1/screenshots/demo-docker2-cmpe172.png)

Screenshots of demo-docker1 and demo-docker2 in Docker:
![docker1-docker](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab1/screenshots/docker1-image-cmpe172.png)

![docker1 logs](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab1/screenshots/docker1-image-logs.png)

![docker1 terminal](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab1/screenshots/docker1-image-terminal.png)

![docker2-docker](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab1/screenshots/docker2-image-cmpe172.png)

## Spring Demo App Using JetBrains Intellij IDEA
**Professors Instructions:**
1. Generate a New Spring Boot Project using the following parameters from IntelliJ IDEA. Store in your Git Folder /labs/lab1/demo-intellij.

Start a New Spring Initializr Project

Configure Project SDK (i.e. Select your JDK 11 install)

Spring Initializr Settings:

- Name: demo-intellij
- Group: com.example
- Location: /labs/lab1/demo-intellij
- Artifact: demo
- Type: Maven
- Language: Java
- Packaging: Jar
- Java Version: 11
- Version: 1.0
- Description: Demo project for Spring Boot
- Package: com.example.demo
 

- Configure Sping Boot Options
    1. Spring Dependencies:
        - Spring Web

2. Make the Appropriate Code Changes using the following:
```sh
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoXXXApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoXXXApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
```

3. Run the Spring Demo App on your Local Machine with the message: Hello CMPE 172!. Take a "Screenshot" of your Browser and Full Desktop and include this in your Lab Notes README.md in GitHub. 

Commands:
- mvn package - Compiles project
- mvn spring-boot:run - runs project

![demo-intellij-image](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab1/screenshots/demo-intellij-cmpe172.png)