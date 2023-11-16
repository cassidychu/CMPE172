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
	java -jar target/spring-gumball-v2-1.0.jar

# Docker

docker-build: build
	docker build -t spring-gumball-v2 .
	docker images

docker-run: docker-build
	docker run --name spring-gumball-v2 -td -p 80:8080 spring-gumball-v2	
	docker ps

docker-clean:
	docker stop spring-gumball-v2
	docker rm spring-gumball-v2
	docker rmi spring-gumball-v2

docker-shell:
	docker exec -it spring-gumball-v2 bash 

docker-push:
	docker login
	docker build -t $(account)/spring-gumball-v2:v1.0 .
	docker push $(account)/spring-gumball-v2:v1.0

# Compose

network-ls:
	docker network ls 

network-create:
	docker network create --driver bridge ${network}

network-prune:
	docker network prune

compose-up:
	docker-compose up --scale gumball=2 -d

compose-down:
	docker-compose down 	

lb-stats:
	echo "user = admin | password = admin"
	open http://localhost:1936

lb-test:
	open http://localhost


# Pod

pod-run:
	kubectl apply -f pod.yaml

pod-list:
	kubectl get pods

pod-desc:
	kubectl describe pods spring-gumball-v2

pod-delete:
	kubectl delete -f pod.yaml

pod-shell:
	kubectl exec -it spring-gumball-v2 -- /bin/bash

pod-logs:
	kubectl logs -f spring-gumball-v2

# Deployment

deployment-create:
	kubectl create -f deployment.yaml --save-config 

deployment-get:
	kubectl get deployments

deployment-get-pods:
	kubectl get pods -l name=spring-gumball-v2

deployment-pod-shell:
	kubectl exec -it $(pod) -- /bin/bash

deployment-upgrade:
	kubectl apply  -f deployment.yaml

deployment-delete:
	kubectl delete deployment spring-gumball-v2-deployment

# Service

service-create:
	kubectl create -f service.yaml

service-get:
	kubectl get services

service-get-ip:
	kubectl get service spring-gumball-v2 -o wide

service-delete:
	kubectl delete service spring-gumball-v2

# Ingress

ingress-apply:
	kubectl apply -f ingress.yaml

ingress-ip:
	kubectl get ingress spring-gumball-v2-ingress