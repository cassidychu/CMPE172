clean:
	mvn clean

compile:
	mvn compile

run: compile
	mvn spring-boot:run

build:
	mvn package

run-jar: build
	java -jar ./start/spring-lombok-1.0.jar

# Docker

docker-build: build
	docker build -t spring-lombok .
	docker images

docker-run: docker-build
	docker run --name spring-lombok -td -p 80:8080 spring-lombok	
	docker ps

up:
	docker-compose up --scale springlombok=2 -d

down:
	docker-compose down

