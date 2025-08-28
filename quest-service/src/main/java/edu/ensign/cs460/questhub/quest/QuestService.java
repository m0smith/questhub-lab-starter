package edu.ensign.cs460.questhub.quest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class QuestService {
    private final Map<Long, Quest> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private static final Set<String> CLASSES = Set.of("Warrior", "Wizard", "Rogue", "All");

    public Quest create(Quest quest) {
        validate(quest);
        long id = idGenerator.getAndIncrement();
        quest.setId(id);
        store.put(id, quest);
        return quest;
    }

    public List<Quest> findAll() {
        return new ArrayList<>(store.values());
    }

    public Quest findById(Long id) {
        Quest q = store.get(id);
        if (q == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quest not found");
        }
        return q;
    }

    private void validate(Quest quest) {
        if (!StringUtils.hasText(quest.getTitle()) || !StringUtils.hasText(quest.getDescription())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title and description required");
        }
        if (quest.getDifficulty() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Difficulty must be >= 1");
        }
        if (!CLASSES.contains(quest.getAllowedClass())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid allowedClass");
        }
    }
}
