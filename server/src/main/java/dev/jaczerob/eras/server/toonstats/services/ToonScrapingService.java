package dev.jaczerob.eras.server.toonstats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jaczerob.eras.server.toonstats.models.ToonHQToon;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@Service
public class ToonScrapingService {
    private static final Pattern TOON_PATTERN = Pattern.compile("\"toon\": (.+?})", Pattern.DOTALL);
    private static final String TOON_HQ_URL = "https://toonhq.org/groups";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public List<ToonHQToon> findToons() {
        final Document document;

        try {
            document = Jsoup.connect(TOON_HQ_URL).get();
        } catch (final IOException exc) {
            log.error("Could not connect to ToonHQ at: {}", TOON_HQ_URL, exc);
            return List.of();
        }

        final Matcher toonMatcher = TOON_PATTERN.matcher(document.html());

        final List<ToonHQToon> toonHQToons = new ArrayList<>();

        while (toonMatcher.find()) {
            final String toonJson = toonMatcher.group(1);
            final ToonHQToon toonHQToon;

            try {
                toonHQToon = OBJECT_MAPPER.readValue(toonJson, ToonHQToon.class);
            } catch (final JsonProcessingException exc) {
                log.error("Could not parse ToonHQ Toon: {}", toonJson, exc);
                continue;
            }

            if (toonHQToon.getId() == null) {
                log.warn("ToonHQ Toon has no ID: {}", toonJson);
                continue;
            } else if (toonHQToon.getPhoto() == null) {
                log.warn("ToonHQ Toon has no photo, potentially unsynced: {}", toonJson);
                continue;
            } else if (toonHQToon.getGame() == null || toonHQToon.getGame() != 1) {
                log.warn("ToonHQ Toon is not a TTR toon: {}", toonJson);
                continue;
            }

            toonHQToons.add(toonHQToon);
        }

        return toonHQToons;
    }
}
