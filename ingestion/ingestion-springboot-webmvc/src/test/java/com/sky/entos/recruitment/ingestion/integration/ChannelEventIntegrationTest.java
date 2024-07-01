package com.sky.entos.recruitment.ingestion.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ChannelEventIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllChannelDetails() throws Exception {
        // When
        ResultActions actions = mockMvc.perform(
                get("/events")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Then
        actions.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(2)))
				.andExpect(jsonPath("$[0].id", equalTo("event-id-2")))
				.andExpect(jsonPath("$[0].title", equalTo("Event Title")))
				.andExpect(jsonPath("$[0].synopsis", equalTo("Event Synopsis")))
				.andExpect(jsonPath("$[0].subtitles", equalTo(null)))
				.andExpect(jsonPath("$[0].audioDescribed", equalTo(null)))
				.andExpect(jsonPath("$[0].hd", equalTo(null)))
				.andExpect(jsonPath("$[0].startTime", equalTo("2024-06-10T17:00:00Z")))
				.andExpect(jsonPath("$[0].duration", equalTo(3456)))
				.andExpect(jsonPath("$[1].id", equalTo("event-id-2")))
				.andExpect(jsonPath("$[1].title", equalTo("Event Title")))
				.andExpect(jsonPath("$[1].synopsis", equalTo("Event Synopsis")))
				.andExpect(jsonPath("$[1].subtitles", equalTo(null)))
				.andExpect(jsonPath("$[1].audioDescribed", equalTo(null)))
				.andExpect(jsonPath("$[1].hd", equalTo(null)))
				.andExpect(jsonPath("$[1].startTime", equalTo("2024-06-10T17:00:00Z")))
				.andExpect(jsonPath("$[1].duration", equalTo(3456)));
    }

    @Test
    public void shouldReturnChannelDetails_ForKnownChannel() throws Exception {
        // Given
        String eventId = "e1234-5678";

        // When
        ResultActions actions = mockMvc.perform(
                get("/events/{eventId}", eventId)
        );

        // Then
        actions.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", equalTo(eventId)))
				.andExpect(jsonPath("$.title", equalTo("Event Title")))
				.andExpect(jsonPath("$.synopsis", equalTo("Event Synopsis")))
				.andExpect(jsonPath("$.subtitles", equalTo(null)))
				.andExpect(jsonPath("$.audioDescribed", equalTo(null)))
				.andExpect(jsonPath("$.hd", equalTo(null)))
				.andExpect(jsonPath("$.startTime", equalTo("2024-06-10T17:00:00Z")))
				.andExpect(jsonPath("$.duration", equalTo(3456)));
    }

	@Test
	public void shouldRaiseNotFoundError_ForUnknownChannel() throws Exception {
		// Given
		String eventId = "unknown-event";

		// When
		ResultActions actions = mockMvc.perform(
				get("/events/{eventId}", eventId)
		);

		// Then
		actions.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status", equalTo(404)))
				.andExpect(jsonPath("$.message", equalTo("Event with id " + eventId + " not found")));
	}
	
}
