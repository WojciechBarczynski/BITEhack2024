package com.example.backend.addiction;

import com.example.backend.addiction.dtos.AddictionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<AddictionDto>> getAllAddictions() {
        var addictions = addictionService.getAllAddictions();
        var addictionsDto = addictions
                .stream()
                .map(addiction -> new AddictionDto(addiction.getId(), addiction.getName()))
                .toList();
        return ResponseEntity.ok(addictionsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddictionDto> getAddiction(@PathVariable("id") int id) {
        var addiction = addictionService.getAddiction(id);
        return ResponseEntity.ok(new AddictionDto(addiction.getId(), addiction.getName()));
    }
}