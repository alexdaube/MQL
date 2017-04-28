import *  as actions from "../actions/tablesActions";

const initialState = {
    tables: [],
    fetching: false,
    error: null
};

export default function (state = initialState, action) {
    switch (action.type) {
        case actions.FETCH_TABLES_PENDING:
            return {...state, fetching: true, error: null};
        case actions.FETCH_TABLES_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.FETCH_TABLES_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.ADD_TABLE_PENDING:
            return {...state, fetching: true, error: null};
        case actions.ADD_TABLE_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.ADD_TABLE_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.ADD_TABLE_ATTRIBUTE_PENDING:
            return {...state, fetching: true, error: null};
        case actions.ADD_TABLE_ATTRIBUTE_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.ADD_TABLE_ATTRIBUTE_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.ADD_TABLE_SYNONYM_PENDING:
            return {...state, fetching: true, error: null};
        case actions.ADD_TABLE_SYNONYM_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.ADD_TABLE_SYNONYM_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.ADD_ATTRIBUTE_SYNONYM_PENDING:
            return {...state, fetching: true, error: null};
        case actions.ADD_ATTRIBUTE_SYNONYM_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.ADD_ATTRIBUTE_SYNONYM_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.REMOVE_TABLE_PENDING:
            return {...state, fetching: true, error: null};
        case actions.REMOVE_TABLE_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.REMOVE_TABLE_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.REMOVE_TABLE_SYNONYM_PENDING:
            return {...state, fetching: true, error: null};
        case actions.REMOVE_TABLE_SYNONYM_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.REMOVE_TABLE_SYNONYM_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.REMOVE_TABLE_ATTRIBUTE_PENDING:
            return {...state, fetching: true, error: null};
        case actions.REMOVE_TABLE_ATTRIBUTE_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.REMOVE_TABLE_ATTRIBUTE_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.REMOVE_ATTRIBUTE_SYNONYM_PENDING:
            return {...state, fetching: true, error: null};
        case actions.REMOVE_ATTRIBUTE_SYNONYM_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.REMOVE_ATTRIBUTE_SYNONYM_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.ADD_TABLE_FOREIGN_KEY_PENDING:
            return {...state, fetching: true, error: null};
        case actions.ADD_TABLE_FOREIGN_KEY_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.ADD_TABLE_FOREIGN_KEY_REJECTED:
            return {...state, error: action.payload, fetching: false};
        case actions.REMOVE_TABLE_FOREIGN_KEY_PENDING:
            return {...state, fetching: true, error: null};
        case actions.REMOVE_TABLE_FOREIGN_KEY_FULFILLED:
            return {...state, tables: action.payload, fetching: false};
        case actions.REMOVE_TABLE_FOREIGN_KEY_REJECTED:
            return {...state, error: action.payload, fetching: false};
        default:
            return state;
    }
}