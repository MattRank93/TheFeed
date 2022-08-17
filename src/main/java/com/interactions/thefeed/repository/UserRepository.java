package com.interactions.thefeed.repository;
import com.interactions.thefeed.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Repository
public interface UserRepository extends MongoRepository<User, String> {


    @Query("{email:'?0'}")
    Optional<User> findOneUserByEmail(String email);


    @Query("{id:'?0'}")
    User findOneById(UUID id);


    @Query("{username:'?0'}")
    User findOneUserByName(String username);

    @Query("{username:'?0'}")
    Boolean existsByUsername(String username);

    @Query("{email:'?0'}")
    Boolean existsByEmail(String email);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<User> findAll(String category);

    public long count();
}