package com.github.jaczerob.madamchuckle.toontown.loaders.news;

import org.springframework.web.client.RestTemplate;

import com.github.jaczerob.madamchuckle.annotations.ToontownAPILoader;
import com.github.jaczerob.madamchuckle.toontown.loaders.Loader;
import com.github.jaczerob.madamchuckle.toontown.loaders.news.models.News;

@ToontownAPILoader("NEWS")
public class NewsLoader implements Loader<News> {
    private final static String URI = "https://www.toontownrewritten.com/api/news";

    @Override
    public News load() {
        return new RestTemplate()
            .getForObject(
                URI,
                News.class
            );
    }
}
