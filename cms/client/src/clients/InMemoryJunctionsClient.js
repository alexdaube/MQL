import Promise from 'promise';

export default class InMemoryJunctionsClient {

    constructor() {
        this.junctions = [];
    }

    fetchJunctions() {
        return new Promise((resolve) => {
            resolve(this.junctions);
        });
    }

    addJunction(junction) {
        return new Promise((resolve) => {
            if (this.junctions.filter(j => j.name === junction.name).length === 0) {
                this.junctions = this.junctions.concat(junction);
            }
            resolve(this.junctions);
        });
    }

    addSynonyms(synonym) {
        return new Promise((resolve) => {
            this.junctions = this.junctions.map(j => {
                if (j.synonyms.filter(s => s === synonym).length === 0) {
                    return {...j, synonyms: j.synonyms.concat(synonym)};
                }
                return j;
            });
            resolve(this.junctions);
        });
    }
}