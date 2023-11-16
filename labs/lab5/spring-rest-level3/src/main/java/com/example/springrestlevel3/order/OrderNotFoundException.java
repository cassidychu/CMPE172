package com.example.springrestlevel3.order;

class OrderNotFoundException extends RuntimeException {

  OrderNotFoundException(Long id) {
    super("Could not find order " + id);
  }
}