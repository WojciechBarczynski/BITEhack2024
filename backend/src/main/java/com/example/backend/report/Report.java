package com.example.backend.report;

import com.example.backend.friend.FriendRelation;
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
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @Getter @Setter private FriendRelation relation;

    @Getter @Setter private Date reportTime;

    @Getter @Setter String postContent;
}
