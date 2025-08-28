package edu.ensign.cs460.questhub.quest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quests")
public class QuestController {
    private final QuestService service;

    public QuestController(QuestService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quest create(@RequestBody Quest quest) {
        return service.create(quest);
    }

    @GetMapping
    public List<Quest> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Quest byId(@PathVariable Long id) {
        return service.findById(id);
    }
}
