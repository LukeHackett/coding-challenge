package com.sky.entos.recruitment.ingestion.controller;

import com.sky.entos.recruitment.ingestion.dto.ChannelEventDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class ChannelEventController {

    @GetMapping
    public List<ChannelEventDto> findAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ChannelEventDto findById(@PathVariable String id) {
        return null;
    }

}
