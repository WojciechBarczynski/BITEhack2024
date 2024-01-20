package com.example.backend.report;

import com.example.backend.friend.FriendRelation;
import com.example.backend.friend.FriendRelationRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue
    @Getter private int id;

    @ManyToOne
    @Getter @Setter private FriendRelation relation;

    @Getter @Setter private Date reportTime;

    @Getter @Setter String postContent;

    public Report(FriendRelation relation, Date date, String postContent){
        this.postContent = postContent;
        this.relation = relation;
        this.postContent = postContent;
    }
}
