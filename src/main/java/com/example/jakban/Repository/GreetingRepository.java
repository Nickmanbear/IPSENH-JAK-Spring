package com.example.jakban.Repository;

import com.example.jakban.model.Greeting;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GreetingRepository extends CrudRepository<Greeting, Integer> {

}