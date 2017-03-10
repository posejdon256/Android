package com.example.annabujak.listazakupow;

/**
 * Created by pawel.bujak on 10.03.2017.
 */

public class Helper {
    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
