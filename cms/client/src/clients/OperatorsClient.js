import axios from "axios";
import Promise from "promise";

import { toDto, fromDto } from './assemblers/operatorAssembler';

export default class OperatorsClient {

    constructor(uri) {
        this.uri = uri;
    }

    fetchOperators() {
        return new Promise((resolve, reject) => {
            axios.get(this.uri).then(response => {
                resolve(response.data.map(fromDto));
            }).catch(error => {
                reject(error);
            });
        });
    }

    addOperator(operator) {
        return axios.post(this.uri, toDto(operator)).then(() => {
            return this.fetchOperators();
        });
    }

    addSynonym(operatorName, synonym) {
        return axios.post(this.uri+"/"+operatorName+"/keywords", {keyword: synonym}).then(() => {
            return this.fetchOperators();
        })
    }

    removeOperator(name) {
        return axios.delete(this.uri + "/" + name).then(() => {
            return this.fetchOperators();
        });
    }

    removeSynonym(operatorName, synonym) {
        return axios.delete(this.uri+"/"+operatorName+"/keywords/"+synonym).then(() => {
            return this.fetchOperators();
        });
    }
}
