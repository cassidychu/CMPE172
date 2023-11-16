/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springscheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.springscheduler.repository.StarbucksDrinkRepository;
import com.example.springscheduler.model.StarbucksDrink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

// Imports for HTTP
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.lang.InterruptedException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Component
@RabbitListener(queues = "drinks")
public class PickupOrder {
	
	private static final Logger log = LoggerFactory.getLogger(PickupOrder.class);

	@Autowired private StarbucksDrinkRepository drinkRepository;

	@RabbitHandler
	public void pickUpMakeDrink(String drinkId) {
		long id = Long.parseLong(drinkId);
		List<StarbucksDrink> drinks = drinkRepository.findById(id);
		if(drinks.isEmpty()) return;

		//Set drink status to in progress
		StarbucksDrink drink = drinks.get(0);
		drink.setStatus("Your drink is being made!");
		drinkRepository.save(drink);
		log.info("Picked up drink: " + drinkId);

		
		try{
			Thread.sleep(60_000);
		} 
		catch (Exception error){

		}
		
		//Set drink status compelete
		drink.setStatus("Your drink is ready!");
		drinkRepository.save(drink);
		log.info("Drink Completed: " + drinkId);
	}


}