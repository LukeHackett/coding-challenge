package com.sky.entos.recruitment.ingestion.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureWebTestClient
public class ChannelIntegrationTest {

	@Autowired
	protected WebTestClient webClient;

    @Test
    public void shouldReturnAllChannelDetails() {
		// When
		WebTestClient.ResponseSpec responseSpec = webClient.get().uri("/channels")
				.accept(MediaType.APPLICATION_JSON)
				.exchange();

		// Then
		responseSpec
				.expectStatus().isOk()
				.expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
				.expectBody()
                .jsonPath("$.length()").value(equalTo(2))
				.jsonPath("$[0].id").value(equalTo("channel-1"))
				.jsonPath("$[0].title").value(equalTo("Channel Title"))
				.jsonPath("$[0].adult").value(equalTo(true))
				.jsonPath("$[0].format").value(equalTo("HD"))
				.jsonPath("$[1].id").value(equalTo("channel-2"))
				.jsonPath("$[1].title").value(equalTo("Channel Title"))
				.jsonPath("$[1].adult").value(equalTo(true))
				.jsonPath("$[1].format").value(equalTo("HD"));
    }

    @Test
    public void shouldReturnChannelDetails_ForKnownChannel() {
        // Given
        String channelId = "channel-123";

        // When
		WebTestClient.ResponseSpec responseSpec = webClient.get().uri("/channels/{channelId}", channelId)
				.accept(MediaType.APPLICATION_JSON)
				.exchange();

		// Then
		responseSpec
				.expectStatus().isOk()
				.expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").value(equalTo(channelId))
				.jsonPath("$.title").value(equalTo("Channel Title"))
				.jsonPath("$.adult").value(equalTo(true))
				.jsonPath("$.format").value(equalTo("HD"));
    }

	@Test
	public void shouldRaiseNotFoundError_ForUnknownChannel() throws Exception {
		// Given
		String channelId = "unknown-channel";

		// When
		WebTestClient.ResponseSpec responseSpec = webClient.get().uri("/channels/{channelId}", channelId)
				.accept(MediaType.APPLICATION_JSON)
				.exchange();

		// Then
		responseSpec
				.expectStatus().isOk()
				.expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.status").value(equalTo(404))
				.jsonPath("$.message").value(equalTo("Channel with id " + channelId + " not found"));
	}

}
