# CMPE 172 - Lab #5 Notes

For this Lab, used these to Configure my Project:
- **Project: Maven Project**
- **Language: Java Language (JDK 11)**
- **Spring Boot Version: (2.7.8)**

## The /labs/lab5 folder includes:
- spring-rest-level2
- spring-grest-level3
- images (screenshots)
- README.md (lab notes)

## Building REST services with Spring
Provided links for lab:
- https://spring.io/guides/tutorials/rest/ 
- https://docs.spring.io/spring-hateoas/docs/1.2.4/api
- https://github.com/spring-projects/spring-hateoas-examples
- https://spring.io/guides/gs/rest-service/ 

**Professors Instructions:**
- Follow the Tutorial from Spring Guides at https://spring.io/guides/tutorials/rest/Links to an external site.. As you build the REST API in the example, test the API using Postman API Development Tool (https://www.postman.com/Links to an external site.). 

- The Spring Tutorial includes the following sections; implement two Spring Projects and include the following parts of the tutorial in each:

    - spring-rest-level2
        - nonrest — Simple Spring MVC app with no hypermedia
    - spring-rest-level3
        - rest — Spring MVC + Spring HATEOAS app with HAL representations of each resource
        - evolution — REST app where a field is evolved but old data is retained for backward compatibility
        - links — REST app where conditional links are used to signal valid state changes to clients

## Spring REST Dependencies
1. Spring Boot DevTools
2. Spring Web
3. Spring Data JPA
4. Spring HATEOAS
5. H2 Database

## Spring-Rest-Level2
**nonrest**
- In spring-rest-level2 this will contain, nonrest — Simple Spring MVC app with no hypermedia.

- These are the requirements to set up spring-rest-level2 in Spring Initializer. This set up can also be done in Intellij. 

![level 2 set up](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/level2-dependencies.png)

- Upon opening up Postman, choose your workspace. I created a new workspace labeled "CMPE 172 Lab Workspace." I chose the option for having only personal access. 

![postman workspace](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/workspace.png)

- In Finder, look for the postman-spring-rest-apis.json that we included in our lab folder. Copy the file and paste it onto your desktop. I personally found it easier to drag and drop it into Postman. 
- Next, at the top left corner in Postman, click on import and drag/drop the postman-spring-rest-apis.json file. SPRINGREST should appear in your collections tab.

![import json](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/import.png)

![spring rest](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/spring-rest.png)

- Click on GET Employees under SPRINGREST. It should be with port 8080. Click the send button to see the results. 

![get employees](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/get-employees.png)

- In Postman when I initially clicked "Send" on the Get Employees tab, I came accross Error: connect ECONNREFUSED 127.0.0.1:8080. I resolved the error by running Spring in my terminal and the error went away. Make sure to run the program first before doing anything on Postman.

![error](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/error.png)

![run spring](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/run-spring.png)

- Now that the error is gone, the employees id, name, and role will be displayed in the body after clicking "Send."

![error gone](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/error-gone.png)

- In the application.properties file, click on the link, provided for H2 DB Console: http://localhost:8080/h2-console. The link will lead you to this page.

![db home](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/db1.png)

- Change the JDBC URL from the default of jdbc:h2:~/test when opening the link to jdbc:h2:mem:cmpe172. 

![jdbc url](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/jdbc-url.png)

- Enter password in the password field.

![login](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/login.png)

- Test the the connection to see that you're connection was successful.

![test connection](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/test-connection.png)

- This is the page once you've connected.

![connected](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/connected.png)

- The attributes: ID, Name, and Role of an Employee are seen when you expand the Employee tab on the left side of the page.

![expand employee](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/expand-employee.png)

- The command "select * from employee" will display the employee's attributes that were sent from Postman when the "Run" button is clicked.

![select employee](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/select-employee.png)

![displayed run](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/displayed-run.png)

- On Postman, in GET Employee 1, when we send the GET, it will display the first employee with the ID #1 in the body. In this instance, it is Bilbo Baggins. (Note: The error displayed on the bottom has already been resolved by running Spring, I just didn't clear the message).

![employee 1](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/employee-1.png)

![bilbo baggins](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/bilbo-baggins.png)

- When we click on Employee 99 and "Send," no employee can be found because there isn't an employee assigned to that ID number. The 404 Not Found appears in PostMan which indicates that it is the proper http response error code.

![employee99](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/employee99.png)

- When we do a GET on a single employee and cannot find it, it will throw and error of EmployeeNotFoundException.

![employee not found](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/employee-not-found.png)

- In CREATE an employee, we need a POST. POST requires a POST body.

![create employee](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/create-employee.png)

![create body](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/create-body.png)

- We copy the structure used in GET Employee for POST, but exclude the ID in CREATE employee.

![structure](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/structure.png)

![exclude id](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/exclude-id.png)

![excluded id](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/excluded-id.png)

![new employee](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/new-employee.png)

- When we run "select * from employee" in the console a new employee appears. 

![run new employee](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/run-new-employee.png)

- In Postman, when we GET employees and "Send," the new employee that was created will now appear. 

![third employee](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/third-employee.png)

- Below is the activity being shown in the terminal.

![terminal activity](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/terminal-activity.png)

## Spring-Rest-Level3
**Professors Instructions:**
1. Copy same code from spring-rest-level2
2. Be sure to use the update code from Paul Nguyen's github repository in CMPE 172 in lab5 under the spring-rest-v2.0
3. Make sure to run spring-rest-level3 before opening a new session in the console and using Postman

**rest**
- Now we will be making using rest — Spring MVC + Spring HATEOAS app with HAL representations of each resource to the API. 

- For starters, adding Spring HATEOAS to dependencies section of pom.xml will give constructs to define a RESTful service and render it acceptable for client consumption.

![hateoas](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/hateoas.png)

- The linkTo command in EmployeeController.java adds link information that provides the API consumer/client hints to possible resources to follow on call for resources you want to update/delete later on.

![link](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/link.png)

- A new session has been opened and now shows the original two employees without changes. 

![new console](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/new-console.png)

![original employees](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/original-employees.png)

- When we GET Employees, there is additional resources added to the data
the api added some additional links relative to the endpoints.

![get level3](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/get-level3.png)

![get level3 employee](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/get-level3-employee.png)

- Click the link provided in self which will get that specific employee
the link in employee is a refrence to the employee container that gets all of the employees. In this instance, I clicked the link from employee 1 which led me to the GET employee 1.

![level3 employee 1](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/level3-employee1.png)

- Go to GET EMPLOYEE 1, SEND, click the first provided link which will lead to a new GET tab. Got to GET EMPLOYEES, click the link for the second employee in self, it leads to a new GET employee tab you can get directly that one employee info.

![get employee1 last](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/get-employee1-last.png)

![last send](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/last-send.png)

![employee 2 link level3](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/employee2-link-level3.png)

![send employee 2 last](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/send-employee2-last.png)

**evolution**
- Now we will be making using evolution — REST app where a field is evolved but old data is retained for backward compatibility.

- As we evolve the API, the purpose is to support both old and new clients. The change in this shows how name becomes firstName and lastName expanding on the attributes of the employee.

![evolution employees](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/evolution-employees.png)

- In the console after refreshing the page and re-entering the password, we can see that first and last name now appear for the employee.

![db evolution](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/db-evolution.png)

- We can create a new employee in Postman and "Send."

![sam created](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/sam-created.png)

- In the console, it shows the new employee when we "select * from employee," their first and last name appear. Samwise Gamgee in this instance is in ID 5 which I will be using to create/update/delete the employee. HATEOAS autoincrements the ID number when creating a new employee.

![sam evolution](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/sam-evolution.png)

- In UPDATE EMPLOYEE 3, I clicked send but changed the employee ID number from 3 to 5. When updating the employee, Samwise Gamgee's role changed from gardener to ring bearer. 

![sam updated](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/sam-updated.png)

![sam ring bearer](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/sam-ring-bearer.png)

- After deleting employee ID 5 of Samwise Gamgee, a HTTP 204 No Content response appears. In the console, Samwise is now removed from the table.

![sam deleted](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/sam-deleted.png)

**links**
- Now we will be making using links — REST app where conditional links are used to signal valid state changes to clients.

- I added a new folder called order which is to cope with state changes without triggering breaking changes in clients. 

- In Postman under GET Orders, the orders appear. In console, when you "select * from customer_order" it will display the table with the orders. The first order shows completed, whereas the second order is in progress. When an order is in progress, it can be cancelled.

![get orders 1](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/get-orders1.png)

![get orders](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/get-orders.png)

![order appear](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/order-appear.png)

- In Postman under CANCEL order, when the "Send" button is clicked, the second order that was in progress is cancelled. 

![cancel order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/cancel-order.png)

- If I tried to send cancel order again, a HTTP 405 Method Not Allowed status code indicating that you cannot cancel an order that has already been cancelled.

![order error](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/order-error.png)

- In Postman under COMPLETE order, a HTTP 200 status code indicated that the action was successful.

![completed order](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab5/screenshots/completed-order.png)