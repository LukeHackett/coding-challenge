package com.sky.entos.recruitment.ingestion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sky.entos.recruitment.ingestion.entity.ChannelFormat;
import lombok.Data;

@Data
public class ChannelDto {

    @JsonProperty
    private String id;

    @JsonProperty
    private String title;

    @JsonProperty
    private boolean adult;

    @JsonProperty
    private ChannelFormat format;
}
