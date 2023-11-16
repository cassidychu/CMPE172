all: clean

clean:
	mvn clean

compile:
	mvn compile

run: compile
	mvn spring-boot:run

build:
	mvn package

run-jar: build
	java -jar target/spring-mysql-1.0.jar

# Docker

docker-build: build
	docker build -t spring-mysql .
	docker images

docker-run: docker-build
	docker run --name spring-mysql -td -p 80:8080 spring-mysql
	docker ps

docker-clean:
	docker stop spring-mysql
	docker rm spring-mysql
	docker rmi spring-mysql

docker-shell:
	docker exec -it spring-mysql bash 

docker-push:
	docker login
	docker build -t $(account)/spring-mysql:v1.0 .
	docker push $(account)/spring-mysql:v1.0
