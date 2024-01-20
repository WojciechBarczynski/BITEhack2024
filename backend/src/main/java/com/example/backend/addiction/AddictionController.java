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

    private final UserRepository userRepository;
    private final AddictionRepository addictionRepository;

    @Autowired
    public AddictionController(UserRepository userRepository, AddictionRepository addictionRepository) {
        this.userRepository = userRepository;
        this.addictionRepository = addictionRepository;
    }

    @PostMapping("/{addictionName}")
    public ResponseEntity<?> addAddiction(@PathVariable("addictionName") String addictionName, @RequestParam Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            Addiction addiction = new Addiction(addictionName);
            addiction.addAddict(user);
            addictionRepository.save(addiction);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}