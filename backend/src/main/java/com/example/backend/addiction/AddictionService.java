package com.example.backend.addiction;

import com.example.backend.addiction.dtos.AddictionDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddictionService {
    private final AddictionRepository addictionRepository;

    public AddictionService(AddictionRepository addictionRepository) {
        this.addictionRepository = addictionRepository;
    }

    public AddictionDto createAddiction(String name) {
        if (addictionRepository.findByName(name).isEmpty()) {
            throw new ResponseStatusException((HttpStatus.CONFLICT));
        }
        var addiction = new Addiction(name);
        addictionRepository.save(addiction);
        return new AddictionDto(addiction.getId(), addiction.getName());
    }

    public List<Addiction> getAllAddictions(){
        return addictionRepository.findAll();
    }
}
