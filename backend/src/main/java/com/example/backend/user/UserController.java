package com.example.backend.user;

import com.example.backend.addiction.dtos.AddictionDto;
import com.example.backend.user.dtos.UserDto;
import com.example.backend.user.requests.LoginRequest;
import com.example.backend.user.requests.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService service) {
        userService = service;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        userService.registerUser(request.nick(), request.password(), request.height(), request.weight(), request.birthyear());
        return ResponseEntity.ok("User added successfully");
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        var user = userService.loginUser(request.nick(), request.password());
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        var users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/addictions")
    public ResponseEntity<List<AddictionDto>> getUserAddictions(@RequestHeader("userId") int userId) {
        var addictions = userService.getUserAddictions(userId);
        return ResponseEntity.ok(addictions);
    }
}

