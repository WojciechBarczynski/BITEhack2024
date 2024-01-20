package com.example.backend.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRelationRepository extends JpaRepository<FriendRelation, Integer> {
    FriendRelation findByAddict_IdAndFriend_IdAndAddiction_Id(int addictId, int friendId, int addictionId);
}