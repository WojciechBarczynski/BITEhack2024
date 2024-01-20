package com.example.backend.user;

import com.example.backend.addiction.Addiction;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(columnList = "nick", unique = true)
})
public class User {
    @Id
    @GeneratedValue
    @Getter
    private int id;
    @Getter @Setter private String nick;

    @Getter @Setter private String password;
    @Getter @Setter private int birthyear;
    @Getter @Setter private int weightKg;
    @Getter @Setter private int heightCm;

    @ManyToMany
    @Getter @Setter private List<Addiction> addictions;

    public User(String nick, String password, int weightKg, int heightCm, int birthyear){
        this.nick = nick;
        this.password = password;
        this.birthyear = birthyear;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.addictions = new ArrayList<>();
    }

    public void addAddiction(Addiction addiction){
        this.addictions.add(addiction);
    }
}
