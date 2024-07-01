package com.sky.entos.recruitment.ingestion.entity;

import lombok.Getter;

@Getter
public enum ChannelFormat {
    HD("HD"), SD("SD"), UHD("4K"), AU("AU"), RA("RA");

    private final String format;

    ChannelFormat(String format) {
        this.format = format;
    }

}