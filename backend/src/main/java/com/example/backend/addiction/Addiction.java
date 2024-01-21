package com.example.backend.addiction;

import com.example.backend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Addiction {
    @Id
    @GeneratedValue
    @Getter
    private int id;

    @Getter
    private String name;

    @ManyToMany
    @Getter
    @Setter
    private List<User> users;

    public Addiction(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    public void addAddict(User addict) {
        users.add(addict);
    }
}
