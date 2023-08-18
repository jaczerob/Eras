package dev.jaczerob.eras.server.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ToontownTime {
    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
    }
}
