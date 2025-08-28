package edu.ensign.cs460.questhub.adventurer;

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
class AdventurerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createAndFetchAdventurer() throws Exception {
        Adventurer adv = new Adventurer(null, "Aragorn", 5, "Warrior");
        mockMvc.perform(post("/adventurers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adv)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        mockMvc.perform(get("/adventurers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Aragorn"));
    }

    @Test
    void validationFailsForBadData() throws Exception {
        Adventurer bad = new Adventurer(null, "", 0, "Mage");
        mockMvc.perform(post("/adventurers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bad)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByIdNotFound() throws Exception {
        mockMvc.perform(get("/adventurers/99"))
                .andExpect(status().isNotFound());
    }
}
