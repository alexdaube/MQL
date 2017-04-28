import * as actions from "../actions/operatorsActions";

const initialState = {
    operators: [],
    fetching: false,
    error: null
};

export default (state = initialState, action) => {
    switch (action.type) {
        case actions.FETCH_OPERATORS_PENDING:
            return {...state, fetching: true, error: null};
        case actions.FETCH_OPERATORS_FULFILLED:
            return {...state, operators: action.payload, fetching: false};
        case actions.FETCH_OPERATORS_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.ADD_OPERATOR_PENDING:
            return {...state, fetching: true, error: null};
        case actions.ADD_OPERATOR_FULFILLED:
            return {...state, operators: action.payload, fetching: false};
        case actions.ADD_OPERATOR_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.ADD_OPERATOR_SYNONYM_PENDING:
            return {...state, fetching: true, error: null};
        case actions.ADD_OPERATOR_SYNONYM_FULFILLED:
            return {...state, operators: action.payload, fetching: false};
        case actions.ADD_OPERATOR_SYNONYM_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.REMOVE_OPERATOR_PENDING:
            return {...state, fetching: true, error: null};
        case actions.REMOVE_OPERATOR_FULFILLED:
            return {...state, operators: action.payload, fetching: false};
        case actions.REMOVE_OPERATOR_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.REMOVE_OPERATOR_SYNONYM_PENDING:
            return {...state, fetching: true, error: null};
        case actions.REMOVE_OPERATOR_SYNONYM_FULFILLED:
            return {...state, operators: action.payload, fetching: false};
        case actions.REMOVE_OPERATOR_SYNONYM_REJECTED:
            return {...state, error: action.payload, fetching: false};
        default:
            return state;
    }
}