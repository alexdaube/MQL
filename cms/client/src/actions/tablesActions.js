import {tablesClient} from "../clients/clients";

export const FETCH_TABLES_PENDING = "FETCH_TABLES_PENDING",
    FETCH_TABLES_FULFILLED = "FETCH_TABLES_FULFILLED",
    FETCH_TABLES_REJECTED = "FETCH_TABLES_REJECTED";

export function fetchTables() {
    return dispatch => {
        dispatch({type: "FETCH_TABLES", payload: tablesClient.fetchTables()});
    }
}

export const ADD_TABLE_PENDING = "ADD_TABLE_PENDING",
    ADD_TABLE_FULFILLED = "ADD_TABLE_FULFILLED",
    ADD_TABLE_REJECTED = "ADD_TABLE_REJECTED";

export function addTable(name) {
    return dispatch => {
        dispatch({
            type: "ADD_TABLE",
            payload: tablesClient.addTable({name: name, synonyms: [], attributes: [], foreignKeys: []})
        })
    }
}

export const ADD_TABLE_ATTRIBUTE_PENDING = "ADD_TABLE_ATTRIBUTE_PENDING",
    ADD_TABLE_ATTRIBUTE_FULFILLED = "ADD_TABLE_ATTRIBUTE_FULFILLED",
    ADD_TABLE_ATTRIBUTE_REJECTED = "ADD_TABLE_ATTRIBUTE_REJECTED";

export function addTableAttribute(tableName, attributeName) {
    return dispatch => {
        dispatch({
            type: "ADD_TABLE_ATTRIBUTE",
            payload: tablesClient.addAttribute(tableName, {name: attributeName, synonyms: []})
        })
    }
}

export const ADD_TABLE_SYNONYM_PENDING = "ADD_TABLE_SYNONYM_PENDING",
    ADD_TABLE_SYNONYM_FULFILLED = "ADD_TABLE_SYNONYM_FULFILLED",
    ADD_TABLE_SYNONYM_REJECTED = "ADD_TABLE_SYNONYM_REJECTED";

export function addTableSynonym(tableName, tableSynonym) {
    return dispatch => {
        dispatch({type: "ADD_TABLE_SYNONYM", payload: tablesClient.addSynonym(tableName, tableSynonym)})
    }
}

export const ADD_ATTRIBUTE_SYNONYM_PENDING = "ADD_ATTRIBUTE_SYNONYM_PENDING",
    ADD_ATTRIBUTE_SYNONYM_FULFILLED = "ADD_ATTRIBUTE_SYNONYM_FULFILLED",
    ADD_ATTRIBUTE_SYNONYM_REJECTED = "ADD_ATTRIBUTE_SYNONYM_REJECTED";

export function addAttributeSynonym(tableName, attributeTable, synonym) {
    return dispatch => {
        dispatch({
            type: "ADD_ATTRIBUTE_SYNONYM",
            payload: tablesClient.addAttributeSynonym(tableName, attributeTable, synonym)
        })
    }
}

export const REMOVE_TABLE_PENDING = "REMOVE_TABLE_PENDING",
    REMOVE_TABLE_FULFILLED = "REMOVE_TABLE_FULFILLED",
    REMOVE_TABLE_REJECTED = "REMOVE_TABLE_REJECTED";

export function removeTable(name) {
    return dispatch => {
        dispatch({type: "REMOVE_TABLE", payload: tablesClient.removeTable(name)})
    }
}

export const REMOVE_TABLE_SYNONYM_PENDING = "REMOVE_TABLE_SYNONYM_PENDING",
    REMOVE_TABLE_SYNONYM_FULFILLED = "REMOVE_TABLE_SYNONYM_FULFILLED",
    REMOVE_TABLE_SYNONYM_REJECTED = "REMOVE_TABLE_SYNONYM_REJECTED";

export function removeTableSynonym(tableName, synonym) {
    return dispatch => {
        dispatch({type: "REMOVE_TABLE_SYNONYM", payload: tablesClient.removeSynonym(tableName, synonym)})
    }
}

export const REMOVE_TABLE_ATTRIBUTE_PENDING = "REMOVE_TABLE_ATTRIBUTE_PENDING",
    REMOVE_TABLE_ATTRIBUTE_FULFILLED = "REMOVE_TABLE_ATTRIBUTE_FULFILLED",
    REMOVE_TABLE_ATTRIBUTE_REJECTED = "REMOVE_TABLE_ATTRIBUTE_REJECTED";

export function removeTableAttribute(tableName, attributeName) {
    return dispatch => {
        dispatch({type: "REMOVE_TABLE_ATTRIBUTE", payload: tablesClient.removeAttribute(tableName, attributeName)})
    }
}

export const REMOVE_ATTRIBUTE_SYNONYM_PENDING = "REMOVE_ATTRIBUTE_SYNONYM_PENDING",
    REMOVE_ATTRIBUTE_SYNONYM_FULFILLED = "REMOVE_ATTRIBUTE_SYNONYM_FULFILLED",
    REMOVE_ATTRIBUTE_SYNONYM_REJECTED = "REMOVE_ATTRIBUTE_SYNONYM_REJECTED";

export function removeAttributeSynonym(tableName, attributeName, synonym) {
    return dispatch => {
        dispatch({
            type: "REMOVE_ATTRIBUTE_SYNONYM",
            payload: tablesClient.removeAttributeSynonym(tableName, attributeName, synonym)
        })
    }
}

export const ADD_TABLE_FOREIGN_KEY_PENDING = "ADD_TABLE_FOREIGN_KEY_PENDING",
    ADD_TABLE_FOREIGN_KEY_FULFILLED = "ADD_TABLE_FOREIGN_KEY_FULFILLED",
    ADD_TABLE_FOREIGN_KEY_REJECTED = "ADD_TABLE_FOREIGN_KEY_REJECTED";

export function addTableForeignKey(fromTableName, fromAttributeName, toTableName, toAttributeName) {
    return dispatch => {
        dispatch({
            type: "ADD_TABLE_FOREIGN_KEY",
            payload: tablesClient.addForeignKey(fromTableName, {
                fromAttribute: fromAttributeName,
                toTable: toTableName,
                toAttribute: toAttributeName
            })
        })
    }
}

export const REMOVE_TABLE_FOREIGN_KEY_PENDING = "REMOVE_TABLE_FOREIGN_KEY_PENDING",
    REMOVE_TABLE_FOREIGN_KEY_FULFILLED = "REMOVE_TABLE_FOREIGN_KEY_FULFILLED",
    REMOVE_TABLE_FOREIGN_KEY_REJECTED = "REMOVE_TABLE_FOREIGN_KEY_REJECTED";

export function removeTableForeignKey(fromTableName, fromAttributeName, toTableName, toAttributeName) {
    return dispatch => {
        dispatch({
            type: "REMOVE_TABLE_FOREIGN_KEY",
            payload: tablesClient.removeForeignKey(fromTableName, {
                fromAttribute: fromAttributeName,
                toTable: toTableName,
                toAttribute: toAttributeName
            })
        })
    }
}

