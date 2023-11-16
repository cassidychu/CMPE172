# CMPE 172 - Lab #2 Notes

For this Lab, used these to Configure my Project:
- **Project: Maven Project**
- **Language: Java Language (JDK 11)**
- **Spring Boot Version: (2.7.8)**

## The /labs/lab2 folder includes:
- spring-mvc
- spring-lombok
- images (screenshots)
- README.md (lab notes)

## Spring MVC Project - Serving Web Content with Spring MVC 
**Professors Instructions:**
1. Build and run the spring-mvc project based on https://spring.io/guides/gs/serving-web-content. You may use the code snippets below (modified from the Tutorial Page).

2. Run the Spring App and take a "Screenshot" of your Browser and Full Desktop and include this in your Lab Notes README.md in GitHub.

**Greeting Controller**
```sh
GreetingController.java:

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}
```

**Greetings View Template (Thymeleaf)**
```sh
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Getting Started: Serving Web Content</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>
```
Commands:
- mvn package - Compiles project
- mvn spring-boot:run - runs project
![mvc load](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/mvc-load.png)
![mvc port](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/mvc-port.png)
![hello world mvc](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/hello-world-mvc.png)

## Spring Lombok
**Professors Instructions:**
1. Build and Test the spring-lombok project based on https://springframework.guru/spring-boot-with-lombok-part-1/. You may use the code snippets below (taken also from the Tutorial Page).

2. Run the Spring App locally and take "Screenshots" of test output (Full Desktop) and include this in your Lab Notes README.md in GitHub. Have Screenshots for each of the tests below along with a discussion in your README.md for the Lab on: a) The Lombok Annonation or Feature being tested and b) How does the Output verify your understanding of the Feature being tested?
    
    - ValAndVarUserDemo.print() ;
    - FieldLevelGetterSetterDemo.print() ;
    - GetterSetterUserDemo.print() ;
    - ConstructorUserDemo.print() ;
    - DataUserDemo.print() ;
    - NonNullUserDemo.print() ;

**SpringLombokApplication.java**
```sh
package com.example.springlombok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringLombokApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLombokApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

		ValAndVarUserDemo.print() ;
		FieldLevelGetterSetterDemo.print() ;
		GetterSetterUserDemo.print() ;
		ConstructorUserDemo.print() ;
		DataUserDemo.print() ;
		NonNullUserDemo.print() ;

		return String.format("Hello %s!", name);
	}

}
```

**ValAndVarUserDemo.java**
```sh
package com.example.springlombok;

import java.math.BigDecimal;
import java.util.ArrayList;
import lombok.val;

public class ValAndVarUserDemo {

    public String valCheck() {
      /*
      val makes local final variable (inside method)
      Trying to assign a value will result in
      Error: java: cannot assign a value to final variable userName
      */
      val userName = "Hello World";
      System.out.println(userName.getClass());
      return userName.toLowerCase();
    }
  
    public Object varCheck() {
      /*
      var makes local variable (inside method).
      Same as var but is not marked final
      */
      var money = new BigDecimal(53.00);
      System.out.println(money.getClass());
      money = new BigDecimal(80.00);
      return money;
    }

    public static void print()
	{
        System.out.println( "\n\n*** ValAndVarUserDemo ***\n") ;
        ValAndVarUserDemo obj = new ValAndVarUserDemo() ;
        System.out.println( "valCheck = " + obj.valCheck() ) ;   
        System.out.println( "varCheck = " + obj.varCheck() ) ;   
	}

  }
```

**FieldLevelGetterSetterDemo.java**
```sh
package com.example.springlombok;

import java.lang.reflect.* ;
import lombok.Getter;
import lombok.Setter;

public class FieldLevelGetterSetterDemo {

    private int userId;

    @Getter @Setter
    private String userName;

    @Getter
    private int userAge;
    
    public FieldLevelGetterSetterDemo(int userAge){
      this.userAge=userAge;
    }

    public static void print()
	{
		System.out.println( "\n\n***** FieldLevelGetterSetterDemo Bytecode Dump *****" ) ;
		FieldLevelGetterSetterDemo m = new FieldLevelGetterSetterDemo(10) ;
		Class gmClass = m.getClass() ;

        System.out.println( "\nFIELDS:" ) ;
        Field[] fields = gmClass.getDeclaredFields();
        for (Field f : fields) {

        	int mods = f.getModifiers();
 
        	if ( Modifier.isPublic(mods) )
        		System.out.format( "  public" ) ;
        	else if ( Modifier.isPrivate(mods) )
        		System.out.format( "  private" ) ;
        	else if ( Modifier.isProtected(mods) )
        		System.out.format( "  protected" ) ;
 
        	if ( Modifier.isStatic(mods) )
        		System.out.format( " static") ;

        	System.out.format( " %s %s\n", f.getType(), f.getName() ) ;

        }

        System.out.println( "\nMETHODS:" ) ;
        Method gmMethods[] = gmClass.getMethods() ;
        for ( int i=0; i <gmMethods.length; i++ )
        {
            Method theMethod = gmMethods[i] ;
            String method = theMethod.toString() ;
            System.out.format( "  %s\n", method ) ;
        }
		
	}  

}
```

**GetterSetterUserDemo.java**
```sh
package com.example.springlombok;

import java.lang.reflect.* ;
import lombok.*;

/*
@Getter and @Setter annotations for getter and setter methods
*/
@Getter
@Setter
public class GetterSetterUserDemo {

  private int userId;
  private String userName;
  private int userAge;

  public static void print()
  {
      System.out.println( "\n\n***** GetterSetterUserDemo Bytecode Dump *****" ) ;
      GetterSetterUserDemo m = new GetterSetterUserDemo() ;
      Class gmClass = m.getClass() ;

      System.out.println( "\nFIELDS:" ) ;
      Field[] fields = gmClass.getDeclaredFields();
      for (Field f : fields) {

          int mods = f.getModifiers();

          if ( Modifier.isPublic(mods) )
              System.out.format( "  public" ) ;
          else if ( Modifier.isPrivate(mods) )
              System.out.format( "  private" ) ;
          else if ( Modifier.isProtected(mods) )
              System.out.format( "  protected" ) ;

          if ( Modifier.isStatic(mods) )
              System.out.format( " static") ;

          System.out.format( " %s %s\n", f.getType(), f.getName() ) ;

      }

      System.out.println( "\nMETHODS:" ) ;
      Method gmMethods[] = gmClass.getMethods() ;
      for ( int i=0; i <gmMethods.length; i++ )
      {
          Method theMethod = gmMethods[i] ;
          String method = theMethod.toString() ;
          System.out.format( "  %s\n", method ) ;
      }
      
  }  

}
```

**ConstructorUserDemo.java**
```sh
package com.example.springlombok;

import java.lang.reflect.* ;
import lombok.*;

/*
@NoArgsConstructor annotation for generating a constructor with no parameters
*/
@NoArgsConstructor
/*
@AllArgsConstructor annotation for generating a constructor
with 1 parameter for each field
*/
@AllArgsConstructor
public class ConstructorUserDemo {
  private int userId;
  private String userName;
  private int userAge;

  public static void print()
  {
      System.out.println( "\n\n***** ConstructorUserDemo Bytecode Dump *****" ) ;
      ConstructorUserDemo m = new ConstructorUserDemo() ;
      Class gmClass = m.getClass() ;

      System.out.println( "\nFIELDS:" ) ;
      Field[] fields = gmClass.getDeclaredFields();
      for (Field f : fields) {

          int mods = f.getModifiers();

          if ( Modifier.isPublic(mods) )
              System.out.format( "  public" ) ;
          else if ( Modifier.isPrivate(mods) )
              System.out.format( "  private" ) ;
          else if ( Modifier.isProtected(mods) )
              System.out.format( "  protected" ) ;

          if ( Modifier.isStatic(mods) )
              System.out.format( " static") ;

          System.out.format( " %s %s\n", f.getType(), f.getName() ) ;

      }

      System.out.println( "\nMETHODS:" ) ;
      Method gmMethods[] = gmClass.getMethods() ;
      for ( int i=0; i <gmMethods.length; i++ )
      {
          Method theMethod = gmMethods[i] ;
          String method = theMethod.toString() ;
          System.out.format( "  %s\n", method ) ;
      }
      
  }  

}
```

**DataUserDemo.java**
```sh
package com.example.springlombok;

import java.lang.reflect.* ;
import lombok.Builder;
import lombok.Data;

@Data
public class DataUserDemo {
  
  private int userId;
  private String userName;
  private int userAge;

  public static void print()
  {
      System.out.println( "\n\n***** DataUserDemo Bytecode Dump *****" ) ;
      DataUserDemo m = new DataUserDemo() ;
      Class gmClass = m.getClass() ;

      System.out.println( "\nFIELDS:" ) ;
      Field[] fields = gmClass.getDeclaredFields();
      for (Field f : fields) {

          int mods = f.getModifiers();

          if ( Modifier.isPublic(mods) )
              System.out.format( "  public" ) ;
          else if ( Modifier.isPrivate(mods) )
              System.out.format( "  private" ) ;
          else if ( Modifier.isProtected(mods) )
              System.out.format( "  protected" ) ;

          if ( Modifier.isStatic(mods) )
              System.out.format( " static") ;

          System.out.format( " %s %s\n", f.getType(), f.getName() ) ;

      }

      System.out.println( "\nMETHODS:" ) ;
      Method gmMethods[] = gmClass.getMethods() ;
      for ( int i=0; i <gmMethods.length; i++ )
      {
          Method theMethod = gmMethods[i] ;
          String method = theMethod.toString() ;
          System.out.format( "  %s\n", method ) ;
      }
      
  }  

}
```

**NonNullUserDemo.java**
```sh
package com.example.springlombok;

import lombok.NonNull;

public class NonNullUserDemo {
  private int userId;
  private String userName;
  private int userAge;

  public NonNullUserDemo(int userId, @NonNull String userName, int userAge) {
    if (userName == null) {
      throw new NullPointerException("userName is marked non-null but is null");
    } else {
      this.userId = userId;
      this.userName = userName;
      this.userAge = userAge;
    }
  }

  public static void print()
  {
    System.out.println( "\n\n***** NonNullUserDemo *****" ) ;

    try {
        NonNullUserDemo val = new NonNullUserDemo(50, null, 25);
    } catch ( Exception e ) {
        System.out.println( e ) ;
    }
   
  }

}

```

**NOTE:** 
you must either comment out all features in HomeController.java except the one you want tested or print each one using the commands below and reading through each output.

Commands:
- mvn package - Compiles project
- mvn spring-boot:run - runs project

![lombok demo](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/lombok-demo.png)

1. 
a) **ValAndVarUserDemo.print()** <br>
b) We are expecting the output of the value "hello world." When using val and var, lombok dynamically allocates the variables type based on its value. For instance, in valCheck(), val figures out that "Hello World" is a String data type and assigns userName as a string value. It will then print the data type class it was assigned to. varCheck() does the same thing except it is not final compared to valCheck(). 

![val and var demo](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/val-and-var.png)

2. 
a) **FieldLevelGetterSetterDemo.print()** <br>
b) Based from the output, we would not be expecting setters or getters to be generated for the userId because it was not tagged with @Getter or @Setter. The methods in the output would not display the set or get userId. Setters and getters would be present for userName with the data type String (can be seen in lines 2 & 4 under METHODS in the screenshot) because it was tagged with @Getter and @Setter. Get userAge would be seen in the output and not set (seen on 3 under METHODS in the screenshot) because it was only tagged with @Getter.

![field level getter setter demo](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/field-level-getter-setter.png)

3. 
a) **GetterSetterUserDemo.print()** <br>
b) We expect to see getter and setters being displayed in the output because all of the variables were tagged with @Getter and @Setter. We expect that userId will display a get and set (lines 3 and 5 under METHODS in the screenshot). The setters will display the data type of the variables used. We expect that userName has a get and set (lines 1 and 6 under METHODS in the screenshot) because it was tagged with @Getter and @Setter. We expect that userAge has a get and set (lines 4 and 7 under METHODS in the screenshot) because of the tags. It is expected that under FIELDS: the fields
of the variables will be displayed whether it is private, public, or protected along with which data type it comes 
from. 

![getter setter user demo](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/getter-setter-user.png)

4. 
a) **ConstructorUserDemo.print()** <br>
b) The @NoArgsConstructor from lombok automatically generate a constructor that contain no arguments. When @AllArgsConstructor is used, lombok will generate constructors with variables in the parameters. The variables, userId, userName, and userAge, are all tagged with @AllArgsConstructor which automatically puts the variables with their data types as parameters when instantiated. The constructor will contain this. with the variable name because it is tagged with @AllArgsConstructor. The output displays no constructor because we did not create one but it does display it's class and method which shows that the tags are working. 

![constructor user demo](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/constructor-user.png)

5. 
a) **DataUserDemo.print()** <br>
b) The output displays everything that is usually found in java objects such as getters, setters, equals, toString, and hashcode because it all the variables were tagged with @Data. Due to the tag, lombok auto generates shortcuts of each annotation for userId, userName, and userAge.

![data user demo](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/data-user.png)

6. 
a) **NonNullUserDemo.print()** <br>
b) The output displays that a null pointer exception was thrown because userName was marked as non-null even though it is null. We expected that userID and userAge would not have a exception thrown because in the parameters of NonNullUserDemo(), userName was the only one with @NonNull which overrides the value. In lombok, @NonNull will generate a null-check statement which in this case checks if userName was null or not and marks it. 

![non null user demo](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab2/screenshots/non-null-user.png)