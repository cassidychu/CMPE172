# CMPE 172 - Lab #9 Notes

- In this Lab, you will will be experimenting with Spring AMQP Messaging using RabbitMQ. You will be deploying a simple one node RabbitMQ on Docker and test your code locally.

- There will be an Appendix with additional materials for deployment to Google Cloud (GKE) for your review. You should practice GKE deployment to prepare for the Final Exam (and for your Project), but deployment to GKE is not a requirement in this Lab.

## The /labs/lab9 folder includes:
- images (screenshots) 
- README.md (lab notes)

## Messaging with RabbitMQ
- [Messaging with RabbitMQ Spring](https://spring.io/guides/gs/messaging-rabbitmq/)
- [Spring AMQP](https://docs.spring.io/spring-amqp/reference/html/)
- Dependencies: Spring for RabbitMQ

## Create a RabbitMQ Message Receiver
**Professors Instructions:**
- Receiver.java
```sh
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}
```

**Register the Listener and Send a Message**
- Spring AMQP’s RabbitTemplate provides everything you need to send and receive messages with RabbitMQ.

- However, you need to:

1. Configure a message listener container.
2. Declare the queue, the exchange, and the binding between them.
3. Configure a component to send some messages to test the listener.

- Spring Boot automatically creates a connection factory and a RabbitTemplate, reducing the amount of code you have to write.

- SpringRabbitmqApplication.java
    - I noticed that there was a slight error in the code the professor provided in the assignment so I changed the line "SpringApplication.run(MessagingRabbitmqApplication.class, args).close();" to "SpringApplication.run(SpringRabbitmqApplication.class, args).close();"

```sh
package com.example.springrabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRabbitmqApplication {

  static final String topicExchangeName = "spring-boot-exchange";

  static final String queueName = "spring-boot";

  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(MessagingRabbitmqApplication.class, args).close();
  }

}
```

**Send a Test Message**
- Runner.java
```sh
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  private final Receiver receiver;

  public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
    this.receiver = receiver;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
  }

}
```

**Configure Application Properties**
```sh
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

**Run the Application**
- The main() method starts that process by creating a Spring application context. This starts the message listener container, which starts listening for messages. There is a Runner bean, which is then automatically run. It retrieves the RabbitTemplate from the application context and sends a Hello from RabbitMQ! message on the spring-boot queue. Finally, it closes the Spring application context, and the application ends.

**Commands:**
    - make network
    - docker network ls
    - make rabbit 
    - make console - this opens localhost:8080
    - make run

![make network rabbit](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/make-network-rabbit.png)

- Use guest as username and guest as password in RabbitMQ console

![rabbitmq-login](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/rabbitmq-login.png)

![rabbitmq-docker](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/rabbitmq-docker.png)

- As you can see under node, it lists the rabbitmq container that is currently running. 

![rabbitmq-overview-part-one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/rabbitmq-overview-part-one.png)

![rabbitmq-queues](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/rabbitmq-queues.png)

- After running the application, "Received <Hello from RabbitMQ!>" appears in the terminal.

![hello-message](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/hello-message.png)

## RabbitMQ Tutorial - Hello World
- [Tutorial One Spring](https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html)
- [Spring Profiles](https://www.baeldung.com/spring-profiles) 
- Dependencies: Spring for RabbitMQ

**Spring Profiles**
- application.properties

```sh
spring.profiles.active=usage_message
tutorial.client.duration=10000
```

- application-dev.properties
```sh
logging.level.org=INFO
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

- application-prod.properties
```sh
logging.level.org=ERROR
spring.rabbitmq.host=<rabbitmq-prod-server>
spring.rabbitmq.port=<rabbitmq-prod-server-port>
spring.rabbitmq.username=<prod-username>
spring.rabbitmq.password=<prod-password>
```
**Running the example**
- Run Example (Makefile)
    - note: pass in env variable to make
    - for exampe: make send env=dev

```sh
send: build
    java -jar target/spring-rabbitmq-helloworld-1.0.jar \
    --spring.profiles.active=$(env),hello,sender

receive: build
    java -jar target/spring-rabbitmq-helloworld-1.0.jar \
    --spring.profiles.active=$(env),hello,receiver
```

**Commands:**
- make send env=dev
- make receive env=dev

- Messages are being displayed after running the application which sends "[x] Sent 'Hello World!'"

![hello-world-spam](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/hello-world-spam.png)

- Now when checking RabbitMQ queue, we see that the hello message was sent 10 times. 

![hello-queue](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/hello-queue.png)

- Now by selecting 'Get Message' in the queue, it pulls a message out. Change the Ack Mode to Automatic ack before pushing the Get Message(s) button. 

![automatic-ack-get](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/automatic-ack-get.png)

![nine-queue](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/nine-queue.png)

![nine-overview](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/nine-overview.png)

- After running the command make receive env=dev, we will get 9 messages in the terminal saying "[x] Received 'Hello World!'"

![received-hello](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/received-hello.png)

- There are 0 messages in the queue now once you refresh the page.

![received-overview](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/received-overview.png)

![zero-queued](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/zero-queued.png)

## RabbitMQ Tutorial - Work Queues
- [Tutorial Two Spring](https://www.rabbitmq.com/tutorials/tutorial-two-spring-amqp.html)
- [Spring Profiles](https://www.baeldung.com/spring-profiles)
- Dependencies: Spring for RabbitMQ


**Spring Profiles**
- application.properties
```sh
spring.profiles.active=usage_message
tutorial.client.duration=10000
```

**Configuring the project**
- Config.java
```sh
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"workers", "work-queues"})
@Configuration
public class Config {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Receiver receiver1() {
            return new Receiver(1);
        }

        @Bean
        public Receiver receiver2() {
            return new Receiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public Sender sender() {
        return new Sender();
    }
}
```

**Sending**
- Sender.java
```sh
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.concurrent.atomic.AtomicInteger;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    AtomicInteger dots = new AtomicInteger(0);

    AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello");
        if (dots.incrementAndGet() == 4) {
            dots.set(1);
        }
        for (int i = 0; i < dots.get(); i++) {
            builder.append('.');
        }
        builder.append(count.incrementAndGet());
        String message = builder.toString();
        template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }

}
```

**Receiving**
- Receiver.java

```sh
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")
public class Receiver {

    private final int instance;

    public Receiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + this.instance +
            " [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
        System.out.println("instance " + this.instance +
            " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
```

**Running the example**
- Run Example (Makefile)
    - note: pass in env variable to make 
    - for exampe: make send env=dev

```sh
send: build
    java -jar target/spring-rabbitmq-workers-1.0.jar \
      --spring.profiles.active=$(env),work-queues,sender

receive: build
    java -jar target/spring-rabbitmq-workers-1.0.jar \
      --spring.profiles.active=$(env),work-queues,receiver

```

**Commands:**
- make send env=dev
- make receive env=dev
- make receive env=dev

- Messages are being displayed after running the application which sends "[x] Sent 'Hello World.#!'" 10 times. As each message was being sent, the number next to hello world increased.

![worker-sent](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-sent.png)

- Now when checking RabbitMQ queues, we see that the hello message was sent 10 times. 

![worker-hello-ten](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-hello-ten.png)

![worker-ten-overview](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-ten-overview.png)

- Here I selected Automatic ack to get 1 message which decreased the messages to 9. 

![get-worker](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/get-worker.png)

![worker-overview-nine](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-overview-nine.png)

- I had to run make receive env=dev twice because it had 2 remaining messages in the queue running it the first time. The second time running the command cleared the queue entirely.

![worker-receive-progress](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-receive-progress.png)

![worker-received](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-received.png)

- This shows the results after running make receive env=dev the first time. Where two messages still remained in queue.

![worker-queue-two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-queue-two.png)

![worker-overview-two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-overview-two.png)

- This shows the results after running make receive env=dev the second time. Where zero messages  remained in queue.

![worker-receive-run-second](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-receive-run-second.png)

![worker-queue-zero](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-queue-zero.png)

![worker-overview-zero](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab9/screenshots/worker-overview-zero.png)

- RabbitMQ would be used to extend the Starbucks API to support an asynchronous order process and you can use it in the Starbucks API for Mobile App and Store Front portion which checks the order status for "Drinks Available" The purpose would be to deploy a message in queue between two applications and take the message from the publisher sending it to the consumer. 