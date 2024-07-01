package com.sky.ems.recruitment;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumeralsDemoTest {
    private final RomanNumeralConverter numerals = new RomanNumeralsDemo();

    @Test
    public void shouldRaiseRuntimeException_WhenProvidingValueLessThan1() {
        // Given
        final int input = 0;

        // When
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            numerals.toNumerals(input);
        });

        // Then
        assertThat(exception.getMessage(), equalTo("Input must be greater than 0"));
    }

    @Test
    public void shouldRaiseRuntimeException_WhenProvidingValueGreaterThan999() {
        // Given
        final int input = 1000;

        // When
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            numerals.toNumerals(input);
        });

        // Then
        assertThat(exception.getMessage(), equalTo("Input must be less than 1000"));
    }

}