package com.example.backend.user;

import com.example.backend.addiction.dtos.AddictionDto;
import com.example.backend.user.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        userRepository = repository;
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

    public List<AddictionDto> getUserAddictions(int userId) {
        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id " + userId);
        }

        return user.get()
                .getAddictions()
                .stream()
                .map(addiction -> new AddictionDto(addiction.getId(), addiction.getName()))
                .toList();
    }
}
