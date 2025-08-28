package edu.ensign.cs460.questhub.adventurer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adventurers")
public class AdventurerController {
    private final AdventurerService service;

    public AdventurerController(AdventurerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Adventurer create(@RequestBody Adventurer adventurer) {
        return service.create(adventurer);
    }

    @GetMapping
    public List<Adventurer> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Adventurer byId(@PathVariable Long id) {
        return service.findById(id);
    }
}
