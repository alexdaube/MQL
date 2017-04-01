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

    addSynonym(operatorName, synonym) {
        return new Promise((resolve) => {
            this.operators = this.operators.map(o => {
                if (operatorName === o.name) {
                    if (o.synonyms.filter(s => s === synonym).length === 0) {
                        return {...o, synonyms: o.synonyms.concat(synonym)};
                    }
                }
                return o;
            });
            resolve(this.operators);
        });
    }

    removeOperator(name) {
        return new Promise((resolve) => {
            this.operators = this.operators.filter(o => o.name !== name);
            resolve(this.operators);
        });
    }

    removeSynonym(operatorName, synonym) {
        return new Promise((resolve) => {
            this.operators = this.operators.map(o => {
                if (o.name === operatorName) {
                    return {...o, synonyms: o.synonyms.filter(s => s !== synonym)};
                }
                return o;
            });
            resolve(this.operators);
        })
    }
}