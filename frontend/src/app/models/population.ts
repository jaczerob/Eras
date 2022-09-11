export class Population {
    error: string;
    lastUpdated: number;
    totalPopulation: number;
    populationByDistrict: Map<String, Number>;
    statusByDistrict: Map<String, String>;

    constructor (error: string, lastUpdated: number, totalPopulation: number, populationByDistrict: Map<String, Number>, statusByDistrict: Map<String, String>) {
        this.error = error;
        this.lastUpdated = lastUpdated;
        this.totalPopulation = totalPopulation;
        this.populationByDistrict = populationByDistrict;
        this.statusByDistrict = statusByDistrict;
    }
}