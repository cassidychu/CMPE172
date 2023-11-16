docker-clean:
  docker stop rabbitmq
  docker rm rabbitmq

network-create:
  docker network create --driver bridge springdemo

network-inspect:
  docker network inspect springdemo

network-ls:
  docker network ls

shell:
  docker exec -it rabbitmq bash 

rabbit:
  docker run --name rabbitmq \
             --network springdemo \
         -p 8080:15672 -p 4369:4369 -p 5672:5672 \
         -d rabbitmq:3-management
console:
  open http://localhost:8080

# Management Console: http://localhost:8080
# username and password of guest / guest:
