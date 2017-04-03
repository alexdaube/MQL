import {junctionsClient} from "../clients/clients";

export const FETCH_JUNCTIONS_PENDING = "FETCH_JUNCTIONS_PENDING",
    FETCH_JUNCTIONS_FULFILLED = "FETCH_JUNCTIONS_FULFILLED",
    FETCH_JUNCTIONS_REJECTED = "FETCH_JUNCTIONS_REJECTED";

export function fetchJunctions() {
    return dispatch => {
        dispatch({type: "FETCH_JUNCTIONS", payload: junctionsClient.fetchJunctions()});
    }
}

export const ADD_JUNCTION_PENDING = "ADD_JUNCTION_PENDING",
    ADD_JUNCTION_FULFILLED = "ADD_JUNCTION_FULFILLED",
    ADD_JUNCTION_REJECTED = "ADD_JUNCTION_REJECTED";

export function addJunction(name) {
    return dispatch => {
        dispatch({type: "ADD_JUNCTION", payload: junctionsClient.addJunction({name: name, synonyms: []})});
    }
}

export const ADD_JUNCTION_SYNONYM_PENDING = "ADD_JUNCTION_SYNONYM_PENDING",
    ADD_JUNCTION_SYNONYM_FULFILLED = "ADD_JUNCTION_SYNONYM_FULFILLED",
    ADD_JUNCTION_SYNONYM_REJECTED = "ADD_JUNCTION_SYNONYM_REJECTED";

export function addJunctionSynonym(junctionName, synonym) {
    return dispatch => {
        dispatch({type: "ADD_JUNCTION_SYNONYM", payload: junctionsClient.addSynonym(junctionName, synonym)});
    }
}

export const REMOVE_JUNCTION_PENDING = "REMOVE_JUNCTION_PENDING",
    REMOVE_JUNCTION_FULFILLED = "REMOVE_JUNCTION_FULFILLED",
    REMOVE_JUNCTION_REJECTED = "REMOVE_JUNCTION_REJECTED";

export function removeJunction(name) {
    return dispatch => {
        dispatch({type: "REMOVE_JUNCTION", payload: junctionsClient.removeJunction(name)});
    }
}

export const REMOVE_JUNCTION_SYNONYM_PENDING = "REMOVE_JUNCTION_SYNONYM_PENDING",
    REMOVE_JUNCTION_SYNONYM_FULFILLED = "REMOVE_JUNCTION_SYNONYM_FULFILLED",
    REMOVE_JUNCTION_SYNONYM_REJECTED = "REMOVE_JUNCTION_SYNONYM_REJECTED";

export function removeJunctionSynonym(junctionName, synonym) {
    return dispatch => {
        dispatch({type: "REMOVE_JUNCTION_SYNONYM", payload: junctionsClient.removeSynonym(junctionName, synonym)});
    }
}