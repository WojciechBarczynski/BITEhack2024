package com.example.backend.friend;

import com.example.backend.addiction.Addiction;
import com.example.backend.user.User;
import jakarta.persistence.*;
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
    @Getter
    @Setter
    private User addict;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private User friend;

    @ManyToOne
    @Getter
    @Setter
    private Addiction addiction;

    public FriendRelation(User addict, User friend, Addiction addiction) {
        this.addict = addict;
        this.friend = friend;
        this.addiction = addiction;
    }
}
