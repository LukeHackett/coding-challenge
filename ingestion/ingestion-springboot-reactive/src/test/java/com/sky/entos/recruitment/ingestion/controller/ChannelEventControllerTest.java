package com.sky.entos.recruitment.ingestion.controller;

import com.sky.entos.recruitment.ingestion.dto.ChannelEventDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
public class ChannelEventControllerTest {

    @InjectMocks
    private ChannelEventController channelEventController;

    @Test
    public void shouldReturnAllChannels() {
        // When
        Flux<ChannelEventDto> eventDtos = channelEventController.findAll();

        // Then
        StepVerifier
                .create(eventDtos)
                .expectNextCount(2)
                .expectNextMatches(Objects::nonNull)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();
    }

    @Test
    public void shouldReturnChannelById() {
        // Given
        String channelEventId = "event-1";

        // When
        Mono<ChannelEventDto> eventDto = channelEventController.findById(channelEventId);

        // Then
        StepVerifier
                .create(eventDto)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();
    }

}
