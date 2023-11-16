all: clean

clean:
	mvn clean

compile:
	mvn compile

build:
	mvn package

run: build
	echo Starting Spring at: http://localhost:8080
	java -jar target/spring-starbucks-api-3.1.jar --spring.profiles.active=dev

run-dev: build
	echo Starting Spring at: http://localhost:8080
	java -jar target/spring-starbucks-api-3.1.jar --spring.profiles.active=dev

run-test: build
	echo Starting Spring at: http://localhost:8080
	java -jar target/spring-starbucks-api-3.1.jar --spring.profiles.active=test
	
default:
	echo "Make Rules: starbucks-network, starbucks-api-run, cashier-nodejs-run, starbucks-app-run"

starbucks-network:
	docker network create --driver bridge starbucks

starbucks-api-run: 
	docker run --network starbucks --name spring-starbucks-api -td -p 8080:8080 paulnguyen/spring-starbucks-api:v3.1	

cashier-nodejs-run:
	docker run --network starbucks --name starbucks-nodejs -p 90:9090  \
	-e "api_endpoint=http://spring-starbucks-api:8080" -td paulnguyen/starbucks-nodejs:v3.0

starbucks-app-run: 
	java -cp starbucks-app.jar \
		-Dapiurl="http://34.72.5.157/api" \
		-Dapikey="Zkfokey2311" \
		-Dregister="5012349" \
		starbucks.Main 2>debug.log

#MySQL DB
mysql:
	docker run -d --network starbucks --name mysql -td -p 3306:3306 -e MYSQL_ROOT_PASSWORD=cmpe172 mysql:8.0

mysql-shell:
	docker exec -it mysql bash

#Docker
docker-build: build
	docker build -t cassidychu/spring-starbucks-api:2.0 .
	docker images

starbucks-network:
	docker network create --driver bridge starbucks

docker-run: docker-build
	docker run --network starbucks -e "MYSQL_HOST=mysql" --name spring-starbucks-api -td -p 8080:8080 spring-starbucks-api

docker-clean:
	docker stop spring-starbucks-api
	docker rm spring-starbucks-api
	docker rmi spring-starbucks-api

docker-shell:
	docker exec -it spring-starbucks-api bash