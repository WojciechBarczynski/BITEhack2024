package com.example.backend.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping()
    public ResponseEntity<?> addFriendRelation(@RequestParam Integer addictId,
                                               @RequestParam Integer friendId,
                                               @RequestParam Integer addictionId) {
        friendService.createFriendRelation(addictId, friendId, addictionId);
        return ResponseEntity.ok().build();
    }
}