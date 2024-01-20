package com.example.backend.friend;

import com.example.backend.addiction.Addiction;
import com.example.backend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class FriendRelation {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private User addict;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private User friend;

    @ManyToOne
    @Getter @Setter
    private Addiction addiction;
}
