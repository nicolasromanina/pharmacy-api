package com.example.narnia.auth.controller;

import com.example.narnia.auth.model.User;
import com.example.narnia.auth.service.UserService;
import com.example.narnia.auth.util.JwtUtil;
import com.example.narnia.auth.exception.UserNotFoundException;
import com.example.narnia.auth.exception.InvalidPasswordException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil; // Injection of JwtUtil for token generation

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            // Handle errors like user already exists, etc.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during registration: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            // Attempt to login the user using email and password
            User loggedInUser = userService.login(user.getEmail(), user.getPassword());

            // Create the JWT token after successful login
            String token = jwtUtil.generateToken(loggedInUser.getEmail());

            // Prepare the response with user info and JWT token
            Map<String, Object> response = new HashMap<>();
            response.put("id", loggedInUser.getId());
            response.put("email", loggedInUser.getEmail());
            response.put("username", loggedInUser.getUsername());
            response.put("token", token); // Include JWT token in the response

            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            // Handle user not found error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        } catch (InvalidPasswordException e) {
            // Handle invalid password error
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid password: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login: " + e.getMessage());
        }
    }

    // Global exception handling for invalid login attempts (Optional)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e.getMessage());
    }
}
