export class ReleaseNotes {
    noteId: number;
    slug: string;
    date: string;
    body: string;

    constructor (noteId: number, slug: string, date: string, body: string) {
        this.noteId = noteId;
        this.slug = slug;
        this.date = date;
        this.body = body;
    }
}