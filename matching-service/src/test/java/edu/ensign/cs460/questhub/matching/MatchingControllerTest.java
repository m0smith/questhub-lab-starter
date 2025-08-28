package edu.ensign.cs460.questhub.matching;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MatchingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void matchFiltersQuests() throws Exception {
        MockRestServiceServer server = MockRestServiceServer.createServer(restTemplate);
        Adventurer adv = new Adventurer(1L, "Gandalf", 5, "Wizard");
        server.expect(MockRestRequestMatchers.requestTo("http://localhost:8081/adventurers/1"))
                .andRespond(MockRestResponseCreators.withSuccess(objectMapper.writeValueAsString(adv), MediaType.APPLICATION_JSON));

        Quest q1 = new Quest(1L, "Easy", 3, 10, "desc", "Wizard");
        Quest q2 = new Quest(2L, "Hard", 10, 50, "desc", "Warrior");
        Quest[] quests = {q1, q2};
        server.expect(MockRestRequestMatchers.requestTo("http://localhost:8082/quests"))
                .andRespond(MockRestResponseCreators.withSuccess(objectMapper.writeValueAsString(quests), MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/match/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$", org.hamcrest.Matchers.hasSize(1)));

        server.verify();
    }
}
