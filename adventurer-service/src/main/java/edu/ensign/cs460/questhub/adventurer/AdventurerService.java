package edu.ensign.cs460.questhub.adventurer;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AdventurerService {
    private final Map<Long, Adventurer> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private static final Set<String> CLASSES = Set.of("Warrior", "Wizard", "Rogue");

    public Adventurer create(Adventurer adventurer) {
        validate(adventurer);
        long id = idGenerator.getAndIncrement();
        adventurer.setId(id);
        store.put(id, adventurer);
        return adventurer;
    }

    public List<Adventurer> findAll() {
        return new ArrayList<>(store.values());
    }

    public Adventurer findById(Long id) {
        Adventurer adv = store.get(id);
        if (adv == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adventurer not found");
        }
        return adv;
    }

    private void validate(Adventurer adventurer) {
        if (!StringUtils.hasText(adventurer.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name must not be blank");
        }
        if (adventurer.getLevel() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Level must be >= 1");
        }
        if (!CLASSES.contains(adventurer.getCharacterClass())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid class");
        }
    }
}
