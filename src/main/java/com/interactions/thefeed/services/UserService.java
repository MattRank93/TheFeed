package com.interactions.thefeed.services;

import com.interactions.thefeed.model.ERole;
import com.interactions.thefeed.model.Role;
import com.interactions.thefeed.model.User;
import com.interactions.thefeed.repository.RoleRepository;
import com.interactions.thefeed.repository.UserRepository;
import com.interactions.thefeed.requestobjects.UserRequest;
import com.interactions.thefeed.responseobjects.UserResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserService {

    @Autowired
    PasswordEncoder encoder;

    private final UserRepository userRepo;

    private final RoleRepository roleRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }


    public ResponseEntity<?> register(@NotNull UserRequest userRequest) {


        try {
            User user = new User(
                    userRequest.getUsername(),
                    userRequest.getEmail(),
                    encoder.encode(userRequest.getPassword()),
                    userRequest.getFirstname(),
                    userRequest.getLastname(),
                    userRequest.getPhone(),
                    ERole.ROLE_USER.name()
            );
            Set<String> strRoles = userRequest.getRoles();
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;
                        case "mod":
                            Role modRole = roleRepo.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
                            break;
                        default:
                            Role userRole = roleRepo.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            user.setRoles(roles);
            userRepo.save(user);
            UserResponse userResponseObject = new UserResponse(userRequest.getUsername(),
                    userRequest.getEmail(),
                    userRequest.getFirstname(),
                    userRequest.getLastname(),
                    userRequest.getPhone());

            return new ResponseEntity<>(userResponseObject, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<?> getAllUsers() {

        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getOneUser(@NotNull UserRequest userRequest) {

        try {
            if (userRequest.getEmail() != null) {
                Optional<User> user = userRepo.findOneUserByEmail(userRequest.getEmail());
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else if (userRequest.getUsername() != null) {
                User user = userRepo.findOneUserByName(userRequest.getUsername());
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(userRequest, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }


    //TODO:Implement user restriction, only the user should be able to delete themselves. or an admin
    public ResponseEntity<?> getOneUserAndDelete(@NotNull UserRequest userRequest) {
        try {
            if (userRequest.getEmail() != null) {
                userRepo.deleteByEmail(userRequest.getEmail());
                return new ResponseEntity<>(userRequest.getEmail(), HttpStatus.OK);
            } else if (userRequest.getUsername() != null) {
                userRepo.deleteByUsername(userRequest.getUsername());
                return new ResponseEntity<>(userRequest.getUsername(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(userRequest, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
