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
public class ChannelEventIntegrationTest {

	@Autowired
	protected WebTestClient webClient;

    @Test
    public void shouldReturnAllChannelDetails() {
		// When
		WebTestClient.ResponseSpec responseSpec = webClient.get().uri("/events")
				.accept(MediaType.APPLICATION_JSON)
				.exchange();

		// Then
		responseSpec
				.expectStatus().isOk()
				.expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
				.expectBody()
                .jsonPath("$.length()").value(equalTo(2))
				.jsonPath("$[0].id").value(equalTo("event-id-2"))
				.jsonPath("$[0].title").value(equalTo("Event Title"))
				.jsonPath("$[0].synopsis").value(equalTo("Event Synopsis"))
				.jsonPath("$[0].subtitles").value(equalTo(null))
				.jsonPath("$[0].audioDescribed").value(equalTo(null))
				.jsonPath("$[0].hd").value(equalTo(null))
				.jsonPath("$[0].startTime").value(equalTo("2024-06-10T17:00:00Z"))
				.jsonPath("$[0].duration").value(equalTo(3456))
				.jsonPath("$[1].id").value(equalTo("event-id-2"))
				.jsonPath("$[1].title").value(equalTo("Event Title"))
				.jsonPath("$[1].synopsis").value(equalTo("Event Synopsis"))
				.jsonPath("$[1].subtitles").value(equalTo(null))
				.jsonPath("$[1].audioDescribed").value(equalTo(null))
				.jsonPath("$[1].hd").value(equalTo(null))
				.jsonPath("$[1].startTime").value(equalTo("2024-06-10T17:00:00Z"))
				.jsonPath("$[1].duration").value(equalTo(3456));
    }

    @Test
    public void shouldReturnChannelDetails_ForKnownChannel() {
        // Given
        String eventId = "e1234-5678";

        // When
		WebTestClient.ResponseSpec responseSpec = webClient.get().uri("/events/{eventId}", eventId)
				.accept(MediaType.APPLICATION_JSON)
				.exchange();

		// Then
		responseSpec
				.expectStatus().isOk()
				.expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").value(equalTo("event-id-2"))
				.jsonPath("$.title").value(equalTo("Event Title"))
				.jsonPath("$.synopsis").value(equalTo("Event Synopsis"))
				.jsonPath("$.subtitles").value(equalTo(null))
				.jsonPath("$.audioDescribed").value(equalTo(null))
				.jsonPath("$.hd").value(equalTo(null))
				.jsonPath("$.startTime").value(equalTo("2024-06-10T17:00:00Z"))
				.jsonPath("$.duration").value(equalTo(3456));
    }

	@Test
	public void shouldRaiseNotFoundError_ForUnknownChannel() {
		// Given
		String eventId = "unknown-event";

		// When
		WebTestClient.ResponseSpec responseSpec = webClient.get().uri("/events/{eventId}", eventId)
				.accept(MediaType.APPLICATION_JSON)
				.exchange();

		// Then
		responseSpec
				.expectStatus().isNotFound()
				.expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.status").value(equalTo(404))
				.jsonPath("$.message").value(equalTo("Event with id " + eventId + " not found"));
	}
	
}
