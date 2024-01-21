package com.example.backend.friend;

import com.example.backend.addiction.dtos.AddictionDto;
import com.example.backend.friend.requests.AddRelationRequest;
import com.example.backend.friend.responses.AllAddictsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping()
    public ResponseEntity<?> addFriendRelation(@RequestHeader("UserID") int addictId, @RequestBody AddRelationRequest request) {
        friendService.createFriendRelation(addictId, request.friendId(), request.addictionId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allAddicts")
    public ResponseEntity<List<AllAddictsResponse>> getAllAddicts(@RequestHeader("UserID") int userId) {
        var response = friendService.getAllAddicts(userId);

        var idToNameMap = new HashMap<Integer, String>();
        var idToAddictionList = new HashMap<Integer, List<AddictionDto>>();

        for (FriendRelation relation : response) {
            var addictId = relation.getAddict().getId();
            var addictName = relation.getAddict().getNick();
            var addictionId = relation.getAddiction().getId();
            var addictionName = relation.getAddiction().getName();

            if (idToNameMap.containsKey(addictId)) {
                idToAddictionList.get(addictId).add(new AddictionDto(addictionId, addictionName));
            } else {
                idToNameMap.put(addictId, addictName);
                var addictionsList = new ArrayList<AddictionDto>();
                addictionsList.add(new AddictionDto(addictionId, addictionName));
                idToAddictionList.put(addictId, addictionsList);
            }
        }

        var allAddictsList = new ArrayList<AllAddictsResponse>();

        for (Map.Entry<Integer, String> entry : idToNameMap.entrySet()) {
            var key = entry.getKey();
            var nick = entry.getValue();
            var listOfAddictions = idToAddictionList.get(key);
            var responseEntry = new AllAddictsResponse(key, nick, listOfAddictions);
            allAddictsList.add(responseEntry);
        }

        return ResponseEntity.ok(allAddictsList);
    }
}