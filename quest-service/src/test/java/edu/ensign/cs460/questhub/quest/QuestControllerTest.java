package edu.ensign.cs460.questhub.quest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class QuestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createAndFetchQuest() throws Exception {
        Quest quest = new Quest(null, "Find Ring", 3, 100, "Find the One Ring", "All");
        mockMvc.perform(post("/quests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        mockMvc.perform(get("/quests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Find Ring"));
    }

    @Test
    void validationFailsForBadData() throws Exception {
        Quest bad = new Quest(null, "", 0, 0, "", "Mage");
        mockMvc.perform(post("/quests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bad)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByIdNotFound() throws Exception {
        mockMvc.perform(get("/quests/99"))
                .andExpect(status().isNotFound());
    }
}
