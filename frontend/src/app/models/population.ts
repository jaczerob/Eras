import { District } from "./district";

export class Population {
    constructor (
        public error: string,
        public lastUpdated: number,
        public totalPopulation: number,
        public districts: District[]) { }
}