package com.example.backend.friend;

import com.example.backend.addiction.AddictionRepository;
import com.example.backend.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FriendService {
    private final FriendRelationRepository friendRelationRepository;
    private final UserRepository userRepository;
    private final AddictionRepository addictionRepository;

    public FriendService(FriendRelationRepository friendRelationRepository, UserRepository userRepository, AddictionRepository addictionRepository) {
        this.friendRelationRepository = friendRelationRepository;
        this.userRepository = userRepository;
        this.addictionRepository = addictionRepository;
    }


    public void createFriendRelation(int addictId, int friendId, int addictionId) {
        var addict = userRepository.findById(addictId);
        if (addict.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + addictId);
        }

        var friend = userRepository.findById(friendId);
        if (friend.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + friendId);
        }

        var addiction = addictionRepository.findById(addictId);
        if (addiction.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No addiction with id " + addictionId);
        }

        var relation = new FriendRelation(addict.get(), friend.get(), addiction.get());
        friendRelationRepository.save(relation);
    }
}
