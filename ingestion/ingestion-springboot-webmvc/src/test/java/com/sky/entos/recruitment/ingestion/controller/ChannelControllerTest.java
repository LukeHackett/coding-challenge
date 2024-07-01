package com.sky.entos.recruitment.ingestion.controller;

import com.sky.entos.recruitment.ingestion.dto.ChannelDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
public class ChannelControllerTest {

    @InjectMocks
    private ChannelController channelController;

    @Test
    public void shouldReturnAllChannels() {
        // When
        List<ChannelDto> channelDtos = channelController.findAll();

        // Then
        assertThat(channelDtos, notNullValue());
    }

    @Test
    public void shouldReturnChannelById() {
        // Given
        String channelId = "channel-1";

        // When
        ChannelDto channelDto = channelController.findById(channelId);

        // Then
        assertThat(channelDto, notNullValue());
    }

}
