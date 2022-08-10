package com.interactions.thefeed.model;

import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User {

    @MongoId
    @Persistent
    private String id;

    @NotBlank( message = "must contain username")
    @Size(max = 100)
    @Indexed(unique=true)
    private String username;

    @NotBlank( message = "must contain email")
    @Size(max = 100)
    @Email
    @Indexed(unique=true)
    private String email;

    @NotBlank( message = "must contain password")
    @Size(max = 120)
    private String password;

    @NotBlank( message = "must contain first name")
    @Size(max = 20)
    private String firstname;

    @NotBlank( message = "must contain last name")
    @Size(max = 20)
    private String lastname;

    @NotBlank
    private String role = "user";


    private String phone;


    public User(String username, String email, String password, String firstname, String lastname, String phone, String role) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }
}
