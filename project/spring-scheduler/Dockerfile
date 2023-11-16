FROM openjdk:11
EXPOSE 8080
ADD ./target/spring-scheduler-1.0.jar /srv/spring-scheduler-1.0.jar
CMD java -jar /srv/spring-scheduler-1.0.jar  --spring.profiles.active=prod