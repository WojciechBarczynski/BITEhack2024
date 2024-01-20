package com.example.backend.addiction;

import com.example.backend.addiction.Addiction;
import com.example.backend.addiction.AddictionRepository;
import com.example.backend.user.User;
import com.example.backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/addiction")
public class AddictionController {
    private final AddictionService addictionService;

    @Autowired
    public AddictionController(AddictionService addictionService) {
        this.addictionService =addictionService;
    }

    @PostMapping("/{addictionName}")
    public ResponseEntity<?> createAddiction(@PathVariable("addictionName") String addictionName) {
        var addiction = addictionService.createAddiction(addictionName);
        return ResponseEntity.ok(addiction);
    }
}