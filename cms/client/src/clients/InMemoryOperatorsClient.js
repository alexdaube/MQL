import Promise from 'promise';

export default class InMemoryOperatorsClient {

    constructor() {
        this.operators = [];
    }

    fetchOperators() {
        return new Promise((resolve) => {
            resolve(this.operators);
        });
    }

    addOperator(operator) {
        return new Promise((resolve) => {
            if (this.operators.filter(o => o.name === operator.name).length === 0) {
                this.operators = this.operators.concat(operator);
            }
            resolve(this.operators);
        });
    }

    addSynonyms(synonym) {
        return new Promise((resolve) => {
            this.operators = this.operators.map(o => {
                if (o.synonyms.filter(s => s === synonym).length === 0) {
                    return {...o, synonyms: o.synonyms.concat(synonym)};
                }
                return o;
            });
            resolve(this.operators);
        });
    }
}