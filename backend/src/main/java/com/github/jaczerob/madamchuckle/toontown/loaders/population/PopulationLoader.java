package com.github.jaczerob.madamchuckle.toontown.loaders.population;

import org.springframework.web.client.RestTemplate;

import com.github.jaczerob.madamchuckle.annotations.ToontownAPILoader;
import com.github.jaczerob.madamchuckle.toontown.loaders.Loader;
import com.github.jaczerob.madamchuckle.toontown.loaders.population.models.Population;

@ToontownAPILoader("POPULATION")
public class PopulationLoader implements Loader<Population> {
    private final static String URI = "https://www.toontownrewritten.com/api/population";

    @Override
    public Population load() {
        return new RestTemplate()
            .getForObject(
                URI,
                Population.class
            );
    }
}
