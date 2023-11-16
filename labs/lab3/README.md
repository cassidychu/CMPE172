# CMPE 172 - Lab #3 Notes

For this Lab, used these to Configure my Project:
- **Project: Maven Project**
- **Language: Java Language (JDK 11)**
- **Spring Boot Version: (2.7.8)**

## The /labs/lab3 folder includes:
- spring-gumball
- images (screenshots)
- README.md (lab notes)

## Part 1 -- Spring Gumball on Docker
**Professors Instructions:**
1. Java State Machine for Gumball Business Logic - The starter code includes a State Machine (Java Pattern) with a JUnit Test. The State Machine Design is as follows and the code is in the "com.example.gumballmachine" package.

**Gumball Spring MVC Code**
1. **GumballModel.java (provided by Professor Paul Nguyen)**
 - This code is currently a "place holder" in version 1 of this example. It does not do much, but will play in important role in future versions.

```sh
package com.example.springgumball;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
class GumballModel {

    private String modelNumber ;
    private String serialNumber ;
    private Integer countGumballs ;
    
}
```
2. **GumballCommand.java**
- The Command Model is used to capture the actions and inputs from POST request from HTML forms in the View. It is using Lombok Framework to generate all the required Java Beans Methods.

```sh 
package com.example.springgumball;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
class GumballCommand {

    private String action ;
    private String message ;
    
}
```

3. **GumballMachineController.java**
- The Gumball Machine Controller handles the GET and POST actions from HTTP, creates and manages business logic via HTTP Sessions and the Gumball Machine State Object.

```sh
package com.example.springgumball;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

import com.example.gumballmachine.GumballMachine ;

@Slf4j
@Controller
@RequestMapping("/")
public class GumballMachineController {

    @GetMapping
    public String getAction( @ModelAttribute("command") GumballCommand command, 
                            Model model, HttpSession session) {
      
        GumballModel g = new GumballModel() ;
        g.setModelNumber( "SB102927") ;
        g.setSerialNumber( "2134998871109") ;
        model.addAttribute( "gumball", g ) ;
        
        GumballMachine gm = new GumballMachine(10) ;
        String message = gm.toString() ;
        session.setAttribute( "gumball", gm) ;
        String session_id = session.getId() ;

        String server_ip = "" ;
        String host_name = "" ;
        try { 
            InetAddress ip = InetAddress.getLocalHost() ;
            server_ip = ip.getHostAddress() ;
            host_name = ip.getHostName() ;
  
        } catch (Exception e) { }
  
        model.addAttribute( "session", session_id ) ;
        model.addAttribute( "message", message ) ;
        model.addAttribute( "server",  host_name + "/" + server_ip ) ;

        return "gumball" ;

    }

    @PostMapping
    public String postAction(@Valid @ModelAttribute("command") GumballCommand command,  
                            @RequestParam(value="action", required=true) String action,
                            Errors errors, Model model, HttpServletRequest request) {
    
        log.info( "Action: " + action ) ;
        log.info( "Command: " + command ) ;
    
        HttpSession session = request.getSession() ;
        GumballMachine gm = (GumballMachine) session.getAttribute("gumball") ;

        if ( action.equals("Insert Quarter") ) {
            gm.insertQuarter() ;
        }

        if ( action.equals("Turn Crank") ) {
            command.setMessage("") ;
            gm.turnCrank() ;
        } 

        session.setAttribute( "gumball", gm) ;
        String message = gm.toString() ;
        String session_id = session.getId() ;        

        String server_ip = "" ;
        String host_name = "" ;
        try { 
            InetAddress ip = InetAddress.getLocalHost() ;
            server_ip = ip.getHostAddress() ;
            host_name = ip.getHostName() ;
  
        } catch (Exception e) { }
  
        model.addAttribute( "session", session_id ) ;
        model.addAttribute( "message", message ) ;
        model.addAttribute( "server",  host_name + "/" + server_ip ) ;
     

        if (errors.hasErrors()) {
            return "gumball";
        }

        return "gumball";
    }

}
```

4. **Gumball HTML View Page**
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
Commands:
- make run - (mvn compile & mvn spring-boot:run) compiles the code to view on localhost

**Gumball Spring Docker Compose**
- **docker-compose.yaml**
    The Docker Compose Spec is used to deployed a load balanced local spring gumball environment on docker. Rules in the Makefile can be used to managed this. Specifically, this commands brings up a load balancer and two insstances of spring gumball spring boot app:
- **docker-compose up --scale gumball=2 -d**

```sh
version: "3"

services:
  gumball:
    image: spring-gumball
    volumes:
      - /tmp:/tmp
    networks:
      - network   
    ports:
      - 8080    
    restart: always     
  lb:
    image: eeacms/haproxy
    depends_on:
    - gumball
    ports:
    - "80:5000"
    - "1936:1936"
    environment:
      BACKENDS: "gumball"
      BACKENDS_PORT: "8080"
      DNS_ENABLED: "true"
      COOKIES_ENABLED: "false"
      LOG_LEVEL: "info"
    networks:
      - network

volumes:
  schemas:
    external: false

networks:
  network:
    driver: bridge
```

More Commands:
- make docker-build - builds the image
- docker-compose up --scale gumball=2 -d - brings up by a scale factor of 2 and causes it to fail, the IP changes between .02 to .03 between refreshes.
- make compose-down - turns the page back to being sticky, IP will stay at .02 now
- docker-compose up --scale gumball=2 -d - brings up the scale factor to being 2
- docker network ls - lists out the docker network(s) to find spring-gumball_network
- docker network inspect spring-gumball_network - displays the IP Addresses for gumball 1 & 2
- docker-compose up --scale gumball=2 -d - created a new gumball 2 in docker after gumball 2 was previously deleted. When a crash is detected, another instances comes back up automatically.
- docker ps - pings to check that port is located at 1936
- curl localhost:8080 - shows that port is running
- docker run --network spring-gumball_network --name jumpbox -t -d ubuntu - creates jumpbox container
- apt-get update - insert code in docker jumpbox terminal to update package
- apt-get install curl - install curl 
- clear - have a fresh terminal
- curl 172.19.0.2:8080 - gumball 2 receives healthy response on docker terminal for jumpbox 
- curl 172.19.0.3:8080 - gumball 1 receives healthy response on docker terminal for jumpbox

**Initial Gumball Screen**
![gumball machine screen](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/gumball-machine-screen.png)

**Inserting Quarter and Turning Crank**
![working machine](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/working-machine.png)

**Testing on new tab that shows Inventory staying the same**
![second gumball screen](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/second-gumball-screen.png)

**Image created in Docker** 
![docker image](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/docker-image.png)

**Signing in and checking load balancer report on the gumball page**
![sign in](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/signin.png)
![load balancer](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/load-balancer.png)
![white label](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/white-label.png)

Due to an error occurring, when reloading the page multiple times, the Gumball Home Pages for Server Host/IP jumps back between showing up as 172.19.0.2 and 172.19.0.2. 
![ip 3](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/ip3.png)
![ip 2](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/ip2.png)

![gumball 1 docker](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/gumball-1-docker.png)

The highlighted portion shows that the error occurred when running gumabll 2. When docker-compose up --scale gumball=2 -d was inputted into the command line, it cause an error of java.lang.NullPointerException : null to arise. When the page is refreshed, a WhiteLabel appears. The error occurred in GumballMachineController.java on line 77 with the command (gm.turnCrank() ;). Since this is a load balance, the code is not sticking but rather failing. 

![gumball 2 docker](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/gumball-2-docker.png)

Changing the COOKIES_ENABLED: "false" to COOKIES_ENABLED: "true" under the docker-compose.yaml, made the code sticky. Now after reloading the localhost repeatedly, the IP will stay at 3 because it is sticky again. 

![cookies](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/cookies.png)

The report shows now that no errors should occur since changing the COOKIES_ENABLED from false to true. The IP is now sticky again staying at 3.
![load balancer 2](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/load-balancer-2.png)
![sticky](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/sticky.png)

![docker network](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/docker-network.png)

![all gumball ip](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/all-gumball-ip.png)

![ping](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/ping.png)

![curl gumball 1](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/curl-gumball-1.png)
![curl gumball 2](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab3/screenshots/curl-gumball-2.png)
