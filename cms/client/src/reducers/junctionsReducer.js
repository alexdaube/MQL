import * as actions from "../actions/junctionsActions";

const initialState = {
    junctions: [],
    fetching: false,
    error: null
};

export default (state = initialState, action) => {
    switch (action.type) {
        case actions.FETCH_JUNCTIONS_PENDING:
            return {...state, fetching: true, error: null};
        case actions.FETCH_JUNCTIONS_FULFILLED:
            return {...state, junctions: action.payload, fetching: false};
        case actions.FETCH_JUNCTIONS_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.ADD_JUNCTION_PENDING:
            return {...state, fetching: true, error: null};
        case actions.ADD_JUNCTION_FULFILLED:
            return {...state, junctions: action.payload, fetching: false};
        case actions.ADD_JUNCTION_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.ADD_JUNCTION_SYNONYM_PENDING:
            return {...state, fetching: true, error: null};
        case actions.ADD_JUNCTION_SYNONYM_FULFILLED:
            return {...state, junctions: action.payload, fetching: false};
        case actions.ADD_JUNCTION_SYNONYM_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.REMOVE_JUNCTION_PENDING:
            return {...state, fetching: true, error: null};
        case actions.REMOVE_JUNCTION_FULFILLED:
            return {...state, junctions: action.payload, fetching: false};
        case actions.REMOVE_JUNCTION_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.REMOVE_JUNCTION_SYNONYM_PENDING:
            return {...state, fetching: true, error: null};
        case actions.REMOVE_JUNCTION_SYNONYM_FULFILLED:
            return {...state, junctions: action.payload, fetching: false};
        case actions.REMOVE_JUNCTION_SYNONYM_REJECTED:
            return {...state, error: action.payload, fetching: false};
        default:
            return state;
    }
}