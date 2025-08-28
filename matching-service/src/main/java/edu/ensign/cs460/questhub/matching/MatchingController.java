package edu.ensign.cs460.questhub.matching;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchingController {
    private final MatchingService service;

    public MatchingController(MatchingService service) {
        this.service = service;
    }

    @GetMapping("/match/{adventurerId}")
    public List<Quest> match(@PathVariable Long adventurerId) {
        return service.match(adventurerId);
    }
}
