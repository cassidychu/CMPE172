package com.example.springstarbucksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/* 
    https://www.baeldung.com/intro-to-project-lombok
    https://www.baeldung.com/lombok-ide
            
    Spring JPA References:

    https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html
    https://docs.spring.io/spring-data/jpa/docs/2.5.1/api
    https://www.baeldung.com/spring-data-rest-relationships
    https://www.baeldung.com/hibernate-one-to-many
    https://www.baeldung.com/jpa-one-to-one
    https://www.baeldung.com/jpa-cascade-types
    https://www.baeldung.com/category/persistence/tag/jpa

*/

@Entity
@Table(name = "STARBUCKS_REGISTER")
@Data
@RequiredArgsConstructor
public class StarbucksRegister {

    private @Id
    @GeneratedValue
    @JsonIgnore  /* https://www.baeldung.com/jackson-ignore-properties-on-serialization */
    Long id;
    
    @Column(unique=true)
    private String register;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore  /* https://www.baeldung.com/jackson-ignore-properties-on-serialization */
    private StarbucksOrder order;


}
