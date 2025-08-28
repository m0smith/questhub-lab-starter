package edu.ensign.cs460.questhub.matching;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchingService {
    private final RestTemplate restTemplate;
    private final String adventurerServiceUrl;
    private final String questServiceUrl;

    public MatchingService(RestTemplate restTemplate,
                           @Value("${adventurer.service.url:http://localhost:8081}") String adventurerServiceUrl,
                           @Value("${quest.service.url:http://localhost:8082}") String questServiceUrl) {
        this.restTemplate = restTemplate;
        this.adventurerServiceUrl = adventurerServiceUrl;
        this.questServiceUrl = questServiceUrl;
    }

    public List<Quest> match(Long adventurerId) {
        Adventurer adv = restTemplate.getForObject(adventurerServiceUrl + "/adventurers/" + adventurerId, Adventurer.class);
        Quest[] quests = restTemplate.getForObject(questServiceUrl + "/quests", Quest[].class);
        if (adv == null || quests == null) {
            return List.of();
        }
        return Arrays.stream(quests)
                .filter(q -> q.getDifficulty() <= adv.getLevel())
                .filter(q -> q.getAllowedClass().equals(adv.getCharacterClass()) || q.getAllowedClass().equals("All"))
                .collect(Collectors.toList());
    }
}
