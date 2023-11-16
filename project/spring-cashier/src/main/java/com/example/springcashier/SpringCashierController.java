package com.example.springcashier;

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
import org.springframework.beans.factory.annotation.Value;

// Imports for HTTP
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//https://fasterxml.github.io/jackson-databind/javadoc/2.7/com/fasterxml/jackson/databind/JsonNode.html
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.lang.InterruptedException;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user/cashier")
public class SpringCashierController {

    @Value("${starbucks.api.host}")
    String starbucksApiHost;
    @Value("${starbucks.api.port}")
    String starbucksApiPort;
    @Value("${kong.enabled}")
    boolean kongEnabled;

    @Value("${kong.api.key}")
    String apikey;
    
    @GetMapping
    public String getAction( @ModelAttribute("command") Command command, 
                            Model model, HttpSession session) {

        String message = "" ;

        command.setRegister( "5012349" ) ;
        message = "Starbucks Reserved Order" + "\n\n" +
            "Register: " + command.getRegister() + "\n" +
            "Status:   " + "Ready for New Order"+ "\n" ;

        String server_ip = "" ;
        String host_name = "" ;
        try { 
            InetAddress ip = InetAddress.getLocalHost() ;
            server_ip = ip.getHostAddress() ;
            host_name = ip.getHostName() ;
        } 
        catch (Exception e) { }

        model.addAttribute( "message", message ) ;
        model.addAttribute( "server",  host_name + "/" + server_ip ) ;

        return "user/starbucks" ;

    }


    @PostMapping
    public String postAction(@Valid @ModelAttribute("command") Command command,  
                            @RequestParam(value="action", required=true) String action,
                            Errors errors, Model model, HttpServletRequest request) throws IOException,
                         JsonProcessingException, InterruptedException {
                        
        String url;
        if(kongEnabled){
            url = "http://" + starbucksApiHost + ":" + starbucksApiPort + "/api";
        } else {
            url = "http://" + starbucksApiHost + ":" + starbucksApiPort;

        }

        
        String message = "" ;

        log.info( "Action: " + action ) ;
        command.setRegister( command.getStores() ) ;
        log.info( "Command: " + command ) ;
        
        /* Setup Http request */
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        String register = command.getRegister();
        /* Process Post Action */
        if ( action.equals("Place Order") ) {
            Order order = new Order();

            order.setDrink(command.getDrinks());
            order.setMilk(command.getMilks());
            order.setSize(command.getSizes());
            System.out.println(order.toString());

            String orderAsString = objectMapper.writeValueAsString(order);

            HttpRequest apiRequest = HttpRequest.newBuilder()
            //https://www.baeldung.com/java-httpclient-post
                .uri(URI.create(url + "/order/register/" + register))
                .POST(HttpRequest.BodyPublishers.ofString(orderAsString))
                .header("Content-Type", "application/json")
                .header("apikey", apikey)
                .build();

            HttpResponse<String> response = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());
            
            int statusCode = response.statusCode();
            String json = response.body();
            JsonNode jsonNode = objectMapper.readTree(json);
            if(statusCode == 400){
                String errorMessage = jsonNode.get("message").asText();
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + register + "\n" +
                    "Status:   " + errorMessage + "\n" ; 
            } 
            else {
                order = objectMapper.readValue(json, Order.class);	
                System.out.println(order.toString());
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Drink: " + order.getDrink() + "\n" +
                    "Milk:  " + order.getMilk() + "\n" +
                    "Size:  " + order.getSize() + "\n" +
                    "Total: " + order.getTotal() + "\n" +
                    "\n" +
                    "Register: " + order.getRegister() + "\n" +
                    "Status:   " + order.getStatus() + "\n" ;
            }            
        }
        else if ( action.equals("Get Order") ) {
            System.out.print(url);
            HttpRequest apiRequest = HttpRequest.newBuilder()
                .uri(URI.create(url +  "/order/register/" + register))
                .GET()
                .header("Content-Type", "application/json")
                .header("apikey", apikey)
                .build();

            HttpResponse<String> response = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());
            
            int statusCode = response.statusCode();
            String json = response.body();
            JsonNode jsonNode = objectMapper.readTree(json);
            if(statusCode == 400){
                String errorMessage = jsonNode.get("message").asText();
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + register + "\n" +
                    "Status:   " + errorMessage + "\n" ; 
            } 
            else {      
                Order order = objectMapper.readValue(json, Order.class);	
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Drink: " + order.getDrink() + "\n" +
                    "Milk:  " + order.getMilk() + "\n" +
                    "Size:  " + order.getSize() + "\n" +
                    "Total: " + order.getTotal() + "\n" +
                    "\n" +
                    "Register: " + order.getRegister() + "\n" +
                    "Status:   " + order.getStatus() + "\n" ;
            }
        } 
        else if ( action.equals("Clear Order") ) {
            HttpRequest apiRequest = HttpRequest.newBuilder()
                .uri(URI.create(url + "/order/register/" + register))
                .DELETE()
                .header("Content-Type", "application/json")
                .header("apikey", apikey)
                .build();

            HttpResponse<String> response = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());
            
            int statusCode = response.statusCode();
            String json = response.body();
            JsonNode jsonNode = objectMapper.readTree(json);
            if(statusCode == 400){
                String errorMessage = jsonNode.get("message").asText();
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + register + "\n" +
                    "Status:   " + errorMessage + "\n" ; 
            } 
            else if(statusCode == 200) {
                String status = jsonNode.get("status").asText();
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + register + "\n" +
                    "Status:   " + status + "\n" ; 
            }
            
                    
        }         
        command.setMessage( message ) ;

        String server_ip = "" ;
        String host_name = "" ;
        try { 
            InetAddress ip = InetAddress.getLocalHost() ;
            server_ip = ip.getHostAddress() ;
            host_name = ip.getHostName() ;
        } 
        catch (Exception e) { }

        model.addAttribute( "message", message ) ;
        model.addAttribute( "server",  host_name + "/" + server_ip ) ;

        return "user/starbucks" ;

    }
    

}

