package com.sky.ems.recruitment;

public interface CaesarCipher {

    String encode(String message, int shift);

    String decode(String cipher, int shift);

}
