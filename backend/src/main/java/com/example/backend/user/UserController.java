package com.example.backend.user;

import com.example.backend.addiction.Addiction;
import com.example.backend.addiction.dtos.AddictionDto;
import com.example.backend.addiction.dtos.UserAddictionDto;
import com.example.backend.report.Report;
import com.example.backend.report.ReportService;
import com.example.backend.user.calcuations.UserReportCalculations;
import com.example.backend.user.dtos.UserDto;
import com.example.backend.user.requests.AddAddictionRequest;
import com.example.backend.user.requests.LoginRequest;
import com.example.backend.user.requests.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ReportService reportService;

    public UserController(UserService userService, ReportService reportService) {
        this.userService = userService;
        this.reportService = reportService;
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
    public ResponseEntity<List<UserAddictionDto>> getUserAddictions(@RequestHeader("UserID") int userId) {
        List<Addiction> addictions = userService.getUserAddictions(userId);
        List<Report> userReports = reportService.getUserReports(userId);
        List<UserAddictionDto> userAddictionDtos = UserReportCalculations.userAddictionDtos(addictions, userReports);

        return ResponseEntity.ok(userAddictionDtos);
    }

    @PostMapping("/addiction")
    public ResponseEntity<String> addAddiction(@RequestHeader("UserID") int userId, @RequestBody AddAddictionRequest request) {
        userService.addAddiction(userId, request.id());
        return ResponseEntity.ok("Addiction added for user");
    }
}

