# CMPE 172 - Lab #4 Notes

Purpose of the lab: I will be exploring Spring Security and Testing them locally on my machine. In addition, I will be making changes to Spring Gumballl (Version 2) and deploying it to Docker Host.  **Google Cloud (GKE) will be a future reference point and not required for this Lab.**

For this Lab, used these to Configure my Project:
- **Project: Maven Project**
- **Language: Java Language (JDK 11)**
- **Spring Boot Version: (2.7.8)**

## The /labs/lab3 folder includes:
- spring-security
- spring-gumball-v2
- images (screenshots)
- README.md (lab notes)

## Securing a Web Application
**Professors Instructions:**
1. Start a Spring Initializr Project in IntelliJ
- Include Spring Security, Spring Web, and Thymeleaf in added dependencies

2. Create HTML Views
- The web application includes two simple views: a home page and a “Hello, World” page.
- src/main/resources/templates/home.html

```sh
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org" xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
        <title>Spring Security Example</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        
        <p>Click <a th:href="@{/hello}">here</a> to see a greeting.</p>
    </body>
</html>
```

- src/main/resources/templates/hello.html

```sh
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
        <title>Hello World!</title>
    </head>
    <body>
        <h1>Hello world!</h1>
    </body>
</html>
```

3. Configure Spring MVC and set up view controllers to expose these templates
- The web application is based on Spring MVC. As a result, you need to configure Spring MVC and set up view controllers to expose these templates. The following listing shows a class that configures Spring MVC in the application:
- MvcConfig.java

```sh
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }
} 
```

- The addViewControllers() method (which overrides the method of the same name in WebMvcConfigurer) adds four view controllers. Two of the view controllers reference the view whose name is home (defined in home.html), and another references the view named hello (defined in hello.html). The fourth view controller references another view named login. You will create that view in the next section.

- At this point, you could jump ahead to “Run the Application” and run the application without having to log in to anything.

- Now that you have an unsecured web application, you can add security to it.
- Suppose that you want to prevent unauthorized users from viewing the greeting page at /hello. As it is now, if visitors click the link on the home page, they see the greeting with no barriers to stop them. You need to add a barrier that forces the visitor to sign in before they can see that page.

4. The following security configuration ensures that only authenticated users can see the secret greeting
- src/main/java/com/example/securingweb/WebSecurityConfig.java

```sh
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
```
- The WebSecurityConfig class is annotated with @EnableWebSecurity to enable Spring Security’s web security support and provide the Spring MVC integration. It also exposes two beans to set some specifics for the web security configuration:

- The SecurityFilterChain bean defines which URL paths should be secured and which should not. Specifically, the / and /home paths are configured to not require any authentication. All other paths must be authenticated (i.e. /hello).  When a user successfully logs in, they are redirected to the previously requested page that required authentication.

- The UserDetailsService bean sets up an in-memory user store with a single user. That user is given a user name of user, a password of password, and a role of USER.

**Run the Application**
Commands: 
- mvn package - creates jar file
- java -jar target/spring-security-0.0.1-SNAPSHOT.jar -  runs project on port 80

Once the application starts up, point your browser to http://localhost:8080.

![welcome](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/welcome.png)

Click on the link, it attempts to take you to the greeting page at /hello. 

![log in](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/login.png)

At the login page, sign in as the test user by entering user and password for the username and password fields. 

![signin](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/sigin.png)

Once you submit the login form, you are authenticated and taken to the greeting page.
![hello world](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/hello-world.png)

## Spring Gumball (Version 2)
**Professors Instructions**
1. Create a new project using Intellij or Spring Initializer
    - Include Spring Boot DevTools, Lombok, Spring Web, Testcontainers, Thymeleaf, and Validation dependencies
2. Start with the initial code for Spring Gumball (Version 2). Note that the code has changes (from Version 1). Eventually switch to Version 2.1 of Spring Gumball when working with HMAC Hash.

**Gumball State Machine Business Logic has been refactored to remove inventory management.**
- GumballMachine.java
```sh
public class GumballMachine {
 
  State soldOutState;
  State noQuarterState;
  State hasQuarterState;
  State soldState;

  State state = noQuarterState ;
 
  public GumballMachine() {
    soldOutState = new SoldOutState(this);
    noQuarterState = new NoQuarterState(this);
    hasQuarterState = new HasQuarterState(this);
    soldState = new SoldState(this);
    state = noQuarterState ;
  }
 
  public void insertQuarter() {
    state.insertQuarter();
  }
 
  public void ejectQuarter() {
    state.ejectQuarter();
  }
 
  public void turnCrank() {
    state.turnCrank();
    state.dispense();
  }

  void setState(State state) {
    this.state = state;
  }
 
  public void setState( String state ) {
        switch ( state ) {
            case "com.example.gumballmachine.NoQuarterState":
                this.state = noQuarterState ;
                break ;
            case "com.example.gumballmachine.HasQuarterState":
                this.state = hasQuarterState ;
                break ;
            case "com.example.gumballmachine.SoldOutState":
                this.state = soldOutState ;
                break ;
            case "com.example.gumballmachine.SoldState":
                this.state = soldState ;
                break ;
            default:
                this.state = noQuarterState ;
                break ;
        }
    }       

  void releaseBall() {
    System.out.println("A gumball comes rolling out the slot...");
  }
 
  void refill(int count) {
    state = noQuarterState;
  }

    public State getState() {
        return state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }
 
  public String toString() {
    StringBuffer result = new StringBuffer();
    result.append("Mighty Gumball, Inc.");
    result.append("\nSpring Boot Standing Gumball Model #2021");
    result.append("\n\n");
    result.append("\nMachine is " + state + "\n");
    return result.toString();
  }
}
```
**Gumball View Template has been modified to add "Hidden Form Fields" for Cookie-Less Tracking purposes.**
- gumball.html
```sh
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" 
      xmlns:th="http://www.thymeleaf.org">

<html>
<head>
    <title>Welcome to the Gumball Machine</title>
    <link rel="stylesheet" th:href="@{/styles.css}" />
</head>

<body>
<h1 align="center">Welcome to the Gumball Machine</h1>

<!-- FORM SECTION -->
<form name="form" th:object="${command}" method="post" action="">

  <input type="hidden" name="state" id="state" th:field="*{state}" />
  <input type="hidden" name="ts" id="ts" th:field="*{timestamp}" />
  <input type="hidden" name="hash" id="hash" th:field="*{hash}" />

    <p>
    <table id="msg" align="center" style="width:40%">
      <tr>
        <td>
          <pre id="pre">
          <span th:text="${message}" />
          </pre>
        </td>
      </tr>
    </table>
    </p>
    <p align="center"> <img th:src="@{/images/giant-gumball-machine.jpg}" width="385" height="316"/></p>
    <br/>
    <p align="center">
        Special Instructions:  <input type="text" id="message" th:field="*{message}"/>
        <br/>
        <br/>
        <br/>
        <input type="submit" name="action" id="btnInsertQuarter" value="Insert Quarter">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" name="action" id="btnTurnCrank" value="Turn Crank">
    </p>
</form>
<!-- END FORM SECTION -->

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

<table align="center" style="width:100%" >
<tr>
  <td style="text-align: left;" >
    <pre>Session ID:  <span th:text="${session}" /></pre>
  </td>
  <td style="text-align: right;" >
    <pre>Server Host/IP:  <span th:text="${server}" /></pre>
  </td>  
</tr>
</table>

</body>
</html>
```

**Gumball Command Model has been updated to include Tracking Fields**
- GumballCommand.java
```sh
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
class GumballCommand {

    private String action ;
    private String message ;
    private String state ;
    private String timestamp ;
    private String hash ;
    
}
```
Commands:
- mvn package - creates jar file
- make run - (mvn compile & mvn spring-boot:run) compiles the code to view on localhost
- make docker build - builds an image for spring-gumball
- docker ps - checks that it contains the correct container ID
- make compose-up - (--scale gumball=2 -d) brings up the scale factor to being 2
- make compose-down - turns the page back to being sticky, IP will stay at 0.3 now
- clear - clears the terminal 
- mvn package - creates jar file
- make run - (mvn compile & mvn spring-boot:run) compiles the code to view on localhost

Make sure to install the Chrome extension for Tamper Dev. This will be used for intercepting, inspecting, and modifying requests before they are sent to the server.

![tamper dev](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/tamper-dev.png)

![starting gumball](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/starting-gumball.png)

Double click on the page to view the source code after clicking on "turn crank." In the highlighted source code, the value shows as NoQuarterState which shoes that currently, no money has been inserted yet. The form/value is put in a hidden field which is similar to cookies. 

![source code no quarter state](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/source-code-no-quarter.png)

After going back to click on the "insert quarter" button, double click on the page to view the source code. The highlighted portion in the source code shows that it is now in the has quarter state because a quarter was inserted into the machine. 

![source code has quarter state](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/source-code-has-quarter.png)

Taking docker images of the page at just localhost:8080. 
![local page](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/local.png)

![docker build](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/docker-build.png)

Output:
![docker ps](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/docker-ps.png)

![docker](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/docker.png)

By using the command make compose-up, it changes the IP with every refresh, making the IP address not sticky (stay the same) anymore. As seen on the bottom right of the page, the ip addresses switch back and forth between 0.228 and 0.3 when the page is refreshed.

![docker compose](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/make-compose.png)

![ip 1](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/ip1.png)

![ip 2](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/ip2.png)

After using the command make compose-down, it makes the IP address sticky again and it stays at 0.3 every time we refresh the page.

![down 1](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/down1.png)

![down 2](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/down2.png)

**Demonstrated Replay Attack**
Stop the current run and re-run the web application. We will now be testing using Tamper Dev. 
- Click on the Insert Quarter, Turn Crack, Insert Quarter, Turn Crank. Leave the state on Turn Crank. In the extension tab, click on Tamper Dev to open. 

![open tamper dev](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/open-tamper-dev.png)

Switch the right button to intercept requests. This will prevent the request from being sent/received and keep it at it's current state despite clicking on the other buttons. 

![intercept](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/intercept.png)

When you try to click on one of the buttons, it will show that the status of the action was intercepted in Tamper Dev.

![intercepted action](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/intercepted-action.png)

Double click on the localhost label that appears in Tamper Dev. This will show when you scroll down that the text displays the current state.

![tamper dev no quarter](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/tamper-dev-no-quarter.png)

In the text body, change the state from NoQuarterState to HasQuarterState. Then submit to send the request. 

![tamper dev has quarter](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/tamper-dev-has-quarter.png)

In your terminal that you used to run the program, it should display the action that was change and submitted. In this case, we intercepted at "turn crank" and since we changed the state in Tamper Dev, our terminal will display that the request went through and the action will say the gumball rolled out successfully. 

![tamper dev result](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/tamper-dev-result.png)

When we rerun the code, turn on the interception, and click on turn the crank twice, our terminal displays the result that we need to pay first. 

![need to pay](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/need-to-pay.png)

A server error is thrown after interception and changing text because the input hash isn't equivalent to the valid hash. 

![different hashes](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/different-hashes.png)

The hash in the payload doesn't match the calculated hash because something was changed. That is why no message of the gumball coming out is shown in the terminal.

![payload no message](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/payload-no-message.png)

**Make Changes to the Gumball to remove the need for sessions**
- Changed line 56 in gumball.html in text from session to hash.

![session id](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/session-id.png)

![hash](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab4/screenshots/hash.png)
