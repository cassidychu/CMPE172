# CMPE 172 - Lab #7 Notes

- In this Lab, you will given some "starter code" for a Spring Payments Page and adding Server Side Form Validation and integration with the CyberSource Payments Gateway. The starter code will also include an Java API Proxy with abstracts all the HTTP REST API and Security functionality for you. This API will need to be integrated with the Spring Payments Project.

For this Lab, used these to Configure my Project:
- **Project: Maven Project**
- **Language: Java Language (JDK 11)**
- **Spring Boot Version: (2.7.8)**

## The /labs/lab7 folder includes:
- spring-cybersource
- spring-payments
- images (screenshots)
- README.md (lab notes)

## Cybersource Account
**Professors Instructions:**
1. Sign-up for a Free Developer Sandbox at Cybersource:  
    - [Free Developer Sandbox Sign Up](https://developer.cybersource.com)
2. Click on "Start with Hello, World!", then "Create Sandbox Account". For Merchant ID, pick something unique (i.e. your GitHub User ID). For Company Name, Address and Email, use San Jose State's and your SJSU.edu email.
3. Account here:
    - [Free Developer Sandbox Sign Up](https://ebc2test.cybersource.com/ebc2/)
4. Log into your Merchant Portal and Generate a Merchant Key
5. Once you've logged in,
    - a. Click on "Payment Configuration"
    - b. Select "Key Management"
    - c. Click Generate Key
    - d. Select Shared Secret Key


![create-key](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/create-key.png)

- Make sure to copy/sabe or download the Key (Key Shared Secret)
![key and shared](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/key-and-shared.png)

- Go back to "Key Management" and the search for "API Keys", Subtype "All Keys"

![transaction management](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/transaction-management.png)

![tm success](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/tm-success.png)

- Note the Key Deatils, this is your Key's Serial Number (aka Key ID).
![key detail](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/key-detail.png)

## Spring Cybersource
- Include no dependencies when creating spring-cybersource in Spring Initializer

- Include this in application.properties

```sh
# https://www.baeldung.com/properties-with-spring

cybersource.merchantkeyid=<keyid>
cybersource.merchantsecretkey=<shared-key>
cybersource.merchantid=<merchant-id>
cybersource.apihost=apitest.cybersource.com
```

- Under the pom.xml file, add these dependencies:

```sh
 <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
        </dependency>
```

- The purpose of adding Jackson allows us to serialize or map java objects to JSON and vice versa. Including Jackson databind, serializes and deserializes the data. It's mainly used to converst JSON. 

## Spring Payments
- Include these dependencies: 
    - Thymeleaf, Lombok, Spring Web, MySQL Driver, H2 Database, Validation, and Spring Data JPA

- In spring-payments, create 2 separate folders, one for spring-cybersource and another for springpayments in the sourcecode folder. 

- Copy all the code used from spring-cybersource into the new folder, you do not need to include SpringCybersourceApplication.java into the folder. 

## Commands
- mvn package
- mvn spring-boot:run

- This is from running spring-payments v1 provided by Professor Paul Nguyen. These are all before altering the code to include validation and catching errors. 

![card first](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/card-first.png)

![hello card](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/hello-card.png)

- Now testing with the most recent version provided by Professor Paul Nguyen, spring-payments-v2.0
- Please note that when you configure spring-payments, apply the Organization ID, API Key Serial Number (Key ID), and API Shared Secret Key provided in the CMPE 172 Slack channel provided by Professor Paul Nguyen to application.properties file. For security reasons, I will not provide the shared key and serial number in the README. 

- After running the code, go to the H2 DB Console: http://localhost:8080/h2-console
    - the username is: sa
    - password is password 
- As you can see when you use the command "SELECT * FROM PAYMENTS" nothing will show us because there is no current data. Also notice that we are using "SELECT * FROM PAYMENTS" instead of "SELECT * FROM PAYMENTS_COMMAND" which was in the first version and that is because the code we currently have overrides it in PaymentsCommand.java using:

```sh
@Table(name="Payments")
```

![select payments empty](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/select-payments-empty.png)

- Below shows that when you don't have anything filled into the input fields, all the inputs required will be listed until all are completed. 

![empty input](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/empty-input.png)

- Be sure when you are filling out the information for the credit card, fully type out the month of the expiration date!

- Do not type 1111-2222-3333-4444 as the credit card number or else an error will be thrown that it is an unsupported credit card. 

- Credit Cart Types supported should be one of the three digits:
    - 001 - Visa
    - 002 - Mastercard, Eurocard1: European regional brand of Mastercard.
    - 003 - American Express
    - 004 - Discover
    - 005 - Diners Club
- This can be determined based on the first digit of the credit card number as follows: American Express(3), Visa (4), Mastercard (5) or Discover (6).

![unsupported card](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/unsupported-card.png)

- In PaymentsController.java, on line 211, I alterd the auth.reference to include my github username so that it shows up next to the order number when we check under Professor Paul Nguyen's cybersource account. 

![github and ordernum](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/github-and-ordernum.png)

- For the credit card number, I used 4111-1111-1111-1111 and it worked perfectly!

![thank you payment](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/thank-you-payment.png)

![order terminal](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/order-terminal.png)

- Now when we check the H2 DB Console using the command "SELECT * FROM PAYMENTS" the order detail appears.

![db order one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/db-order-one.png)

![db order two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/db-order-two.png)

- Logging into Professor Paul Nguyen's cybersource account based on the information provided in the demo we can see that my order went through with my github ID next to it. My order are the first two that show up. It will be under Transaction Management and then click on Transactions. 

![transaction](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/transaction.png)

![transaction detail](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab7/screenshots/transaction-detail.png)

- Lombok is used in this lab to automatically generate getters and setters using Lombok annotations. 
- ThymeLeaf is used in this lab as it's a Java template used to process and create HTML, XML, JavaScript, CSS, and text. This is used in spring-payments which allows you to use the variables from the creditcard.html form and reference them. 
- Jackson is used in the code for this lab because it maps java objects to JSON and vice versa. This can be seen in AuthResponse.java, CaptureResponse.java, Payload.java, and RefundResponse.java under the spring-cybersource folder as they all import com.fasterxml.jackson.databind.ObjectMapper; or 
import com.fasterxml.jackson.databind.JsonNode;.

- In the pom.xml file, these are the jackson dependencies added:
```sh
<dependency>		
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
</dependency>
```