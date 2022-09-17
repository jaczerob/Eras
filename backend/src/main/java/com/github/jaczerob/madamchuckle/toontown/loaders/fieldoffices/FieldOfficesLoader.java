package com.github.jaczerob.madamchuckle.toontown.loaders.fieldoffices;

import org.springframework.web.client.RestTemplate;

import com.github.jaczerob.madamchuckle.annotations.ToontownAPILoader;
import com.github.jaczerob.madamchuckle.toontown.loaders.Loader;
import com.github.jaczerob.madamchuckle.toontown.loaders.fieldoffices.models.FieldOffices;

@ToontownAPILoader("FIELD_OFFICES")
public class FieldOfficesLoader implements Loader<FieldOffices> {
    private final static String URI = "https://www.toontownrewritten.com/api/fieldoffices";

    @Override
    public FieldOffices load() {
        return new RestTemplate()
            .getForObject(
                URI,
                FieldOffices.class
            );
    }
}
