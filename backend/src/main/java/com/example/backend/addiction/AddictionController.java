package com.example.backend.addiction;

import com.example.backend.addiction.dtos.AddictionDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addiction")
public class AddictionController {
    private final AddictionService addictionService;

    @Autowired
    public AddictionController(AddictionService addictionService) {
        this.addictionService = addictionService;
    }

    @PostMapping("/{addictionName}")
    public ResponseEntity<?> createAddiction(@PathVariable("addictionName") String addictionName) {
        var addiction = addictionService.createAddiction(addictionName);
        return ResponseEntity.ok(addiction);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddictionDto>> getAllAddictions(){
        var addictions = addictionService.getAllAddictions();
        var addictionsDto = addictions
                .stream()
                .map(addiction -> new AddictionDto(addiction.getId(), addiction.getName()))
                .toList();
        return ResponseEntity.ok(addictionsDto);
    }
}