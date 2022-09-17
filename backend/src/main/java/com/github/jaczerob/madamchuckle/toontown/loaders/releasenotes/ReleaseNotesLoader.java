package com.github.jaczerob.madamchuckle.toontown.loaders.releasenotes;

import org.springframework.web.client.RestTemplate;

import com.github.jaczerob.madamchuckle.annotations.ToontownAPILoader;
import com.github.jaczerob.madamchuckle.toontown.loaders.Loader;
import com.github.jaczerob.madamchuckle.toontown.loaders.releasenotes.models.ReleaseNotes;
import com.github.jaczerob.madamchuckle.toontown.loaders.releasenotes.models.ReleaseNotesPartial;

@ToontownAPILoader("RELEASE_NOTES")
public class ReleaseNotesLoader implements Loader<ReleaseNotes> {
    private final static String URI_ALL = "https://www.toontownrewritten.com/api/releasenotes";
    private final static String URI_ONE = "https://www.toontownrewritten.com/api/releasenotes/%d";

    @Override
    public ReleaseNotes load() {
        ReleaseNotesPartial[] partials = new RestTemplate()
            .getForObject(
                URI_ALL,
                ReleaseNotesPartial[].class
            );

        return new RestTemplate()
        .getForObject(
            String.format(URI_ONE, partials[0].getNoteId()),
            ReleaseNotes.class
        );
    }
}
