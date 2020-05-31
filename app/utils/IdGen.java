package utils;

import java.util.UUID;

public class IdGen {
    public static String get() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
