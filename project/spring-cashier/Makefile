all: clean

clean:
	mvn clean

compile:
	mvn compile

run: compile
	mvn spring-boot:run

build:
	mvn package

run-build:
	mvn:spring-boot:run

docker-mysql-start:
	docker run -d --name mysql-midterm -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0

docker-build: build
	docker build --platform=linux/amd64 -t spring-cashier .
	docker images

#Jumpbox
jumpbox:
	docker run --network spring-cashier_network --name jumpbox -t -d ubuntu

# docker run --name jumpbox --network=spring-cashier_network  -t -d ubuntu
compose-up:
	docker-compose up --scale cashier=2

lb-up:
	docker-compose up -d lb

mysql-up:
	docker-compose up -d mysql

compose-down:
	docker-compose down

#MySQL DB
mysql-shell:
	docker exec -it mysql bash