package com.sky.entos.recruitment.ingestion.controller;

import com.sky.entos.recruitment.ingestion.dto.ChannelDto;
import com.sky.entos.recruitment.ingestion.dto.ChannelEventDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
public class ChannelEventControllerTest {

    @InjectMocks
    private ChannelEventController channelEventController;

    @Test
    public void shouldReturnAllChannels() {
        // When
        List<ChannelEventDto> eventDtos = channelEventController.findAll();

        // Then
        assertThat(eventDtos, notNullValue());
    }

    @Test
    public void shouldReturnChannelById() {
        // Given
        String channelEventId = "event-1";

        // When
        ChannelEventDto eventDto = channelEventController.findById(channelEventId);

        // Then
        assertThat(eventDto, notNullValue());
    }

}
