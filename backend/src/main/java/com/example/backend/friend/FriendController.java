package com.example.backend.friend;

import com.example.backend.addiction.Addiction;
import com.example.backend.addiction.AddictionRepository;
import com.example.backend.user.User;
import com.example.backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final UserRepository userRepository;
    private final FriendRelationRepository friendRelationRepository;
    private final AddictionRepository addictionRepository;

    @Autowired
    public FriendController(UserRepository userRepository, FriendRelationRepository friendRelationRepository, AddictionRepository addictionRepository) {
        this.userRepository = userRepository;
        this.friendRelationRepository = friendRelationRepository;
        this.addictionRepository = addictionRepository;
    }

    @PostMapping("/")
    public ResponseEntity<?> addFriendRelation(@RequestParam Integer addictId,
                                               @RequestParam Integer friendId,
                                               @RequestParam Integer addictionId) {
        Optional<User> addictOptional = userRepository.findById(addictId);
        Optional<User> friendOptional = userRepository.findById(friendId);
        Optional<Addiction> addictionOptional = addictionRepository.findById(addictionId);

        if (addictOptional.isPresent() && friendOptional.isPresent() && addictionOptional.isPresent()) {
            FriendRelation friendRelation = new FriendRelation();
            friendRelation.setAddict(addictOptional.get());
            friendRelation.setFriend(friendOptional.get());
            friendRelation.setAddiction(addictionOptional.get());
            friendRelationRepository.save(friendRelation);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}