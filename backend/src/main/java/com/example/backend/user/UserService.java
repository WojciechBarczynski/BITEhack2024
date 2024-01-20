package com.example.backend.user;

import com.example.backend.addiction.Addiction;
import com.example.backend.addiction.AddictionRepository;
import com.example.backend.user.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddictionRepository addictionRepository;

    public UserService(UserRepository userRepository, AddictionRepository addictionRepository) {
        this.userRepository = userRepository;
        this.addictionRepository = addictionRepository;
    }

    public void registerUser(String nick, String password, int height, int weight, int birthyear) {
        var checkUser = userRepository.findByNick(nick);
        if (checkUser != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with such username already exists in database");
        }

        var user = new User(nick, password, height, weight, birthyear);
        userRepository.save(user);
    }

    public UserDto loginUser(String nick, String password) {
        var checkUser = userRepository.findByNick(nick);
        if (checkUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with nickname " + nick + " found");
        }

        if (!checkUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Provided password was incorrect");
        }

        return new UserDto(checkUser.getId(), checkUser.getNick());
    }

    public List<UserDto> getAll() {
        var users = userRepository.findAll();
        return users.stream().map(user -> new UserDto(user.getId(), user.getNick())).toList();
    }

    public List<Addiction> getUserAddictions(int userId) {
        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + userId);
        }

        return user.get()
                .getAddictions();
    }

    public User getUser(int userId) {
        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + userId);
        }

        return user.get();
    }

    public void addAddiction(int userId, int addictionId) {
        var user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user");
        }

        var addiction = addictionRepository.findById(addictionId);

        if (addiction.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such addiction");
        }

        var concreteUser = user.get();
        var concreteAddiction = addiction.get();

        concreteUser.addAddiction(concreteAddiction);
        concreteAddiction.addAddict(concreteUser);

        addictionRepository.save(concreteAddiction);
        userRepository.save(concreteUser);
    }
}
