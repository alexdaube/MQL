import axios from "axios";
import Promise from "promise";

import { toDto, fromDto } from './assemblers/junctionAssembler';

export default class JunctionsClient {

    constructor(uri) {
        this.uri = uri;
    }

    fetchJunctions() {
        return new Promise((resolve, reject) => {
            axios.get(this.uri).then(response => {
                resolve(response.data.map(j => fromDto(j)));
            }).catch(error => {
                reject(error);
            });
        });
    }

    addJunction(junction) {
        return axios.post(this.uri, toDto(junction)).then(() => {
            return this.fetchJunctions();
        });
    }

    addSynonym(junctionName, synonym) {
        return axios.post(this.uri+"/"+junctionName+"/keywords", {keyword: synonym}).then(() => {
            return this.fetchJunctions();
        })
    }

    removeJunction(name) {
        return axios.delete(this.uri + "/" + name).then(() => {
            return this.fetchJunctions();
        });
    }

    removeSynonym(junctionName, synonym) {
        return axios.delete(this.uri+"/"+junctionName+"/keywords/"+synonym).then(() => {
            return this.fetchJunctions();
        });
    }
}