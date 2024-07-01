package com.sky.entos.recruitment.ingestion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ChannelEventDto {

    @JsonProperty
    private String id;

    @JsonProperty
    private String title;

    @JsonProperty
    private String synopsis;

    @JsonProperty
    private boolean subtitles;

    @JsonProperty
    private boolean audioDescribed;

    @JsonProperty
    private boolean hd;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private OffsetDateTime startTime;

    @JsonProperty
    private long duration;

}