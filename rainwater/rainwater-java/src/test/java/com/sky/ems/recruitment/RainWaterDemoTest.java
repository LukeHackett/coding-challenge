package com.sky.ems.recruitment;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RainWaterDemoTest {
    private final RainWater rainWater = new RainWaterDemo();

    @Test
    public void shouldReturnZero_WhenInputArrayIsEmpty() {
        // Given
        Integer[] data = {};

        // When
        Integer value = rainWater.capture(data);

        // Then
        assertThat(value, equalTo(0));
    }

    @Test
    public void shouldReturnCorrectValues() {
        // Given
        Integer[] data = { 4, 2, 1, 3, 0, 1, 2 };

        // When
        Integer value = rainWater.capture(data);

        // Then
        assertThat(value, equalTo(6));
    }

}