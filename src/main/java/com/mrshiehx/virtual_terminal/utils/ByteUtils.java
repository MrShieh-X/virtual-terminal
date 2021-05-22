package com.mrshiehx.virtual_terminal.utils;

public class ByteUtils {
    private ByteUtils() {
    }

    public static boolean isBytesAllZero(byte[] bytes) {
        int i = 0;
        for (byte bytea : bytes) {
            if (bytea == (byte) 0) i++;
        }
        return i == bytes.length;
    }
}
