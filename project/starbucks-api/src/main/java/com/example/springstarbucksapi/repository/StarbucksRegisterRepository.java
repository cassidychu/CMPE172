package com.example.springstarbucksapi.repository;

/* https://docs.spring.io/spring-data/jpa/docs/2.4.6/reference/html/#repositories */

import com.example.springstarbucksapi.model.StarbucksRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarbucksRegisterRepository extends JpaRepository<StarbucksRegister, Long> {

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods
    // https://docs.spring.io/spring-data/data-commons/docs/current/reference/html/#repositories.query-methods.query-creation

    StarbucksRegister findByRegister(String register);

    void deleteByRegister(String register);
}
