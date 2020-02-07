package com.capstone.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.capstone.model.User;


@Repository
public interface CapstoneRepository extends ReactiveCrudRepository<User, String> {

}
