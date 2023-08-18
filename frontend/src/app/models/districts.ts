import { District } from "./district";

export class Districts {
    constructor(
        public totalPopulation: number,
        public districts: District[],
    ) {}
}