import {News} from "./news";
import {Districts} from "./districts";
import {ReleaseNotes} from "./release-notes";
import {FieldOffices} from "./field-offices";
import {Status} from "./status";
import {ToonStats} from "./toonstats";
import {v4 as uuid} from "uuid";

export class TTRAPIResponse {
  public id: string = uuid();

  constructor(
    public news: News,
    public releaseNotes: ReleaseNotes,
    public fieldOffices: FieldOffices,
    public status: Status,
    public toonStats: ToonStats,
    public districts: Districts,
  ) {
  }
}
