package com.example.backend.friend;

import com.example.backend.friend.requests.AddRelationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public ResponseEntity<?> addFriendRelation(@RequestHeader("UserID") int friendId, @RequestBody AddRelationRequest request) {
        friendService.createFriendRelation(request.addictId(), friendId, request.addictionId());
        return ResponseEntity.ok().build();
    }
}