package dev.jaczerob.eras.server.toontown.models.news;

import dev.jaczerob.eras.server.utils.ToontownObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class News extends ToontownObject {
    private String title;
    private int postId;
    private String author;
    private String body;
    private String date;
    private String image;
}
