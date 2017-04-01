import { operatorsClient } from '../clients/clients'

export const FETCH_OPERATORS_PENDING = "FETCH_OPERATORS_PENDING",
    FETCH_OPERATORS_FULFILLED = "FETCH_OPERATORS_FULFILLED",
    FETCH_OPERATORS_REJECTED = "FETCH_OPERATORS_REJECTED";

export function fetchOperators() {
    return dispatch => {
        dispatch({type: "FETCH_OPERATORS", payload: operatorsClient.fetchOperators()});
    }
}

export const ADD_OPERATOR_PENDING = "ADD_OPERATOR_PENDING",
    ADD_OPERATOR_FULFILLED = "ADD_OPERATOR_FULFILLED",
    ADD_OPERATOR_REJECTED = "ADD_OPERATOR_REJECTED";

export function addOperator(name) {
    return dispatch => {
        dispatch({type: "ADD_OPERATOR", payload: operatorsClient.addOperator({name: name, synonyms: []})});
    }
}

export const ADD_OPERATOR_SYNONYM_PENDING = "ADD_OPERATOR_SYNONYM_PENDING",
    ADD_OPERATOR_SYNONYM_FULFILLED = "ADD_OPERATOR_SYNONYM_FULFILLED",
    ADD_OPERATOR_SYNONYM_REJECTED = "ADD_OPERATOR_SYNONYM_REJECTED";

export function addOperatorSynonym(operatorName, synonym) {
    return dispatch => {
        dispatch({type: "ADD_OPERATOR_SYNONYM", payload: operatorsClient.addSynonym(operatorName, synonym)});
    }
}

export const REMOVE_OPERATOR_PENDING = "REMOVE_OPERATOR_PENDING",
    REMOVE_OPERATOR_FULFILLED = "REMOVE_OPERATOR_FULFILLED",
    REMOVE_OPERATOR_REJECTED = "REMOVE_OPERATOR_REJECTED";

export function removeOperator(name) {
    return dispatch => {
        dispatch({type: "REMOVE_OPERATOR", payload: operatorsClient.removeOperator(name)});
    }
}

export const REMOVE_OPERATOR_SYNONYM_PENDING = "REMOVE_OPERATOR_SYNONYM_PENDING",
    REMOVE_OPERATOR_SYNONYM_FULFILLED = "REMOVE_OPERATOR_SYNONYM_FULFILLED",
    REMOVE_OPERATOR_SYNONYM_REJECTED = "REMOVE_OPERATOR_SYNONYM_REJECTED";

export function removeOperatorSynonym(operatorName, synonym) {
    return dispatch => {
        dispatch({type: "REMOVE_OPERATOR_SYNONYM", payload: operatorsClient.removeSynonym(operatorName, synonym)});
    }
}