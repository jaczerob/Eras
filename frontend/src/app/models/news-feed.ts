import {ReleaseNotes} from "./release-notes";
import {Status} from "./status";
import {News} from "./news";
import {Districts} from "./districts";

export class NewsFeed {
  constructor(
    public districts: Districts,
    public news: News,
    public releaseNotes: ReleaseNotes,
    public status: Status,
  ) {
  }
}
