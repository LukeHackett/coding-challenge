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
public class ChannelIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllChannelDetails() throws Exception {
        // When
        ResultActions actions = mockMvc.perform(
                get("/channels")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Then
        actions.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", equalTo(2)))
				.andExpect(jsonPath("$[0].id", equalTo("channel-1")))
				.andExpect(jsonPath("$[0].title", equalTo("Channel Title")))
				.andExpect(jsonPath("$[0].adult", equalTo(true)))
				.andExpect(jsonPath("$[0].format", equalTo("HD")))
				.andExpect(jsonPath("$[1].id", equalTo("channel-2")))
				.andExpect(jsonPath("$[1].title", equalTo("Channel Title")))
				.andExpect(jsonPath("$[1].adult", equalTo(true)))
				.andExpect(jsonPath("$[1].format", equalTo("HD")));
    }

    @Test
    public void shouldReturnChannelDetails_ForKnownChannel() throws Exception {
        // Given
        String channelId = "channel-123";

        // When
        ResultActions actions = mockMvc.perform(
                get("/channels/{channelId}", channelId)
        );

        // Then
        actions.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", equalTo(channelId)))
				.andExpect(jsonPath("$.title", equalTo("Channel Title")))
				.andExpect(jsonPath("$.adult", equalTo(true)))
				.andExpect(jsonPath("$.format", equalTo("HD")));
    }

	@Test
	public void shouldRaiseNotFoundError_ForUnknownChannel() throws Exception {
		// Given
		String channelId = "unknown-channel";

		// When
		ResultActions actions = mockMvc.perform(
				get("/channels/{channelId}", channelId)
		);

		// Then
		actions.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status", equalTo(404)))
				.andExpect(jsonPath("$.message", equalTo("Channel with id " + channelId + " not found")));
	}

}
