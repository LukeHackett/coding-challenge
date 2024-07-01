package com.sky.ems.recruitment;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CaesarCipherDemoTest {
    private final CaesarCipher caesar = new CaesarCipherDemo();

    @Test
    public void shouldEncodeMessage_Basic() {
        // Given
        String message = "AbC";
        int shift = 3;

        // When
        String cipher = caesar.encode(message, shift);

        // Then
        assertThat(cipher, equalTo("DEF"));
    }

    @Test
    public void shouldEncodeMessage_Advanced() {
        // Given
        String message = "QT34C- 1W";
        int shift = 10;

        // When
        String cipher = caesar.encode(message, shift);

        // Then
        assertThat(cipher, equalTo("03ABMHJ_6"));
    }

    @Test
    public void shouldDecodeMessage_Basic() {
        // Given
        String cipher = "DKR";
        int shift = 3;

        // When
        String message = caesar.decode(cipher, shift);

        // Then
        assertThat(message, equalTo("AHO"));
    }

    @Test
    public void shouldDecodeMessage_Advanced() {
        // Given
        String message = "A7D 4-_GD";
        int shift = 7;

        // When
        String cipher = caesar.encode(message, shift);

        // Then
        assertThat(cipher, equalTo("HBKG_EFNK"));
    }
}