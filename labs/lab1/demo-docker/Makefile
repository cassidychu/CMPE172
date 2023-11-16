all: clean


clean:
	mvn clean

compile:
	mvn compile

run: compile
	mvn spring-boot:run

build:
	mvn package

run-jar:
	java -jar target/demo-docker-1.0.jar


run: build
	echo Starting Spring at:  http://localhost:8080
	java -jar ./start/demo-docker-1.0.jar

# Docker
docker-clean:
	docker rm demo-docker1
	docker rm demo-docker2
	
docker-build: build
	docker build -t demo-docker .
	docker images

docker-run: docker-build
	docker run --name demo-docker1 -td -p 81:8080 demo-docker
	docker run --name demo-docker2 -td -p 82:8080 demo-docker
	docker ps

docker-clean:
	docker stop spring-hello
	docker rm spring-hello
	docker rmi spring-hello

docker-shell:
	docker exec -it demo-docker1 bash 
