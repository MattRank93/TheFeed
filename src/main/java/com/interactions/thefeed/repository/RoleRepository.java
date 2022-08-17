package com.interactions.thefeed.repository;

import java.util.Optional;

import com.interactions.thefeed.model.ERole;
import com.interactions.thefeed.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
