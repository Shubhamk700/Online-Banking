package com.banking.online_banking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;   // ✅ FIXED import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.online_banking.entity.User;
import com.banking.online_banking.repository.UserRepository;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    // Get all accounts (users)
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Deposit money
    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable Long id, @RequestParam Double amount) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setBalance(user.getBalance() + amount);
            userRepository.save(user);
            return "Deposited " + amount + " to user " + user.getUsername();
        }
        return "User not found";
    }

    // Withdraw money
    @PostMapping("/{id}/withdraw")
    public String withdraw(@PathVariable Long id, @RequestParam Double amount) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                userRepository.save(user);
                return "Withdrawn " + amount + " from user " + user.getUsername();
            } else {
                return "Insufficient balance!";
            }
        }
        return "User not found";
    }
}
