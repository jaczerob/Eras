package com.github.jaczerob.madamchuckle.toontown.loaders.status;

import org.springframework.web.client.RestTemplate;

import com.github.jaczerob.madamchuckle.annotations.ToontownAPILoader;
import com.github.jaczerob.madamchuckle.toontown.loaders.Loader;
import com.github.jaczerob.madamchuckle.toontown.loaders.status.models.Status;

@ToontownAPILoader("STATUS")
public class StatusLoader implements Loader<Status> {
    private final static String URI = "https://www.toontownrewritten.com/api/status";

    @Override
    public Status load() {
        return new RestTemplate()
            .getForObject(
                URI,
                Status.class
            );
    }
}
