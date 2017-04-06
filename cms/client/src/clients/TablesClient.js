import Promise from "promise";
import axios from 'axios';
import {fromDto, toDto, toAttributeDto, toForeignKeyDto} from "./assemblers/tableAssembler";


export default class InMemoryTablesClient {

    constructor(uri) {
        this.uri = uri;
    }

    fetchTables() {
        return new Promise((resolve, reject) => {
            axios.get(this.uri).then(response => {
                resolve(response.data.map(fromDto));
            }).catch(error => {
                reject(error);
            });
        });
    }

    addTable(table) {
        return axios.post(this.uri, toDto(table)).then(() => {
            return this.fetchTables();
        });
    }

    addAttribute(tableName, attribute) {
        return axios.post(this.uri + "/" + tableName + "/columns", toAttributeDto(attribute)).then(() => {
            return this.fetchTables();
        })
    }

    addSynonym(tableName, synonym) {
        return axios.post(this.uri + "/" + tableName + "/keywords", {keyword: synonym}).then(() => {
            return this.fetchTables();
        });
    }

    addForeignKey(fromTableName, foreignKey) {
        return axios.post(this.uri + "/" + fromTableName + "/foreign_keys", toForeignKeyDto(foreignKey)).then(() => {
            return this.fetchTables();
        });
    }

    addAttributeSynonym(tableName, attributeName, synonym) {
        return axios.post(this.uri + "/" + tableName + "/columns/" + attributeName + "/keywords", {keyword: synonym}).then(() => {
            return this.fetchTables();
        });
    }

    removeTable(name) {
        return axios.delete(this.uri + "/" + name).then(() => {
            return this.fetchTables();
        });
    }

    removeAttribute(tableName, attributeName) {
        return axios.delete(this.uri + "/" + tableName + "/columns/" + attributeName).then(() => {
            return this.fetchTables();
        });
    }

    removeSynonym(tableName, synonym) {
        return axios.delete(this.uri + "/" + tableName + "/keywords/" + synonym).then(() => {
            return this.fetchTables();
        });
    }

    removeForeignKey(fromTableName, foreignKey) {
        return axios.post(this.uri + "/" + fromTableName + "/foreign_keys/remove", toForeignKeyDto(foreignKey)).then(() => {
            return this.fetchTables();
        });
    }

    removeAttributeSynonym(tableName, attributeName, synonym) {
        return axios.delete(this.uri + "/" + tableName + "/columns/" + attributeName + "/keywords/" + synonym).then(() => {
            return this.fetchTables();
        });
    }
}