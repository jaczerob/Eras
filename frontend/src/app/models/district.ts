import { Invasion } from "./invasion";

export class District {
    public static OFFLINE = 'offline';
    public static ONLINE = 'online';
    public static DRAINING = 'draining';
    public static CLOSED = 'closed';

    public static DRAINING_COLOR = 'orange';
    public static OFFLINE_COLOR = 'black';

    public static FULL = 500;
    public static BUSY = 300;

    public static FULL_COLOR = 'red';
    public static BUSY_COLOR = 'green';
    public static EMPTY_COLOR = 'blue';

    constructor(
        public name: string,
        public population: number,
        public status: string,
        public invasion: Invasion | null,
    ) {}
}