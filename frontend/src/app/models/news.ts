export class News {
    title: string;
    postId: number;
    author: string;
    body: string;
    date: string;
    image: string;

    constructor (title: string, postId: number, author: string, body: string, date: string, image: string) {
        this.title = title;
        this.postId = postId;
        this.author = author;
        this.body = body;
        this.date = date;
        this.image = image;
    }
}