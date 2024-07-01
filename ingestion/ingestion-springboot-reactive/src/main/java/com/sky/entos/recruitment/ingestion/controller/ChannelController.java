package com.sky.entos.recruitment.ingestion.controller;

import com.sky.entos.recruitment.ingestion.dto.ChannelDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    @GetMapping
    public Flux<ChannelDto> findAll() {
        return null;
    }

    @GetMapping("/{id}")
    public Mono<ChannelDto> findById(@PathVariable String id) {
        return null;
    }

}
