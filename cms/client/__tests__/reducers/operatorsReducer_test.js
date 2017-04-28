import operatorsReducer from "../../src/reducers/operatorsReducer";
import * as actions from "../../src/actions/operatorsActions";

const PAYLOAD = ["A payload"];

let state;

beforeEach(() => {
    state = {operators: [], fetching: false, error: null};
});

test('Given a FETCH_OPERATORS_PENDING action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.FETCH_OPERATORS_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a ADD_OPERATOR_PENDING action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.ADD_OPERATOR_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a ADD_OPERATOR_SYNONYM_PENDING action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.ADD_OPERATOR_SYNONYM_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a REMOVE_OPERATOR_PENDING action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.REMOVE_OPERATOR_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a REMOVE_OPERATOR_SYNONYM_PENDING action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.REMOVE_OPERATOR_SYNONYM_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a FETCH_OPERATORS_FULFILLED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.FETCH_OPERATORS_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, operators: PAYLOAD});
});

test('Given a ADD_OPERATOR_FULFILLED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.ADD_OPERATOR_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, operators: PAYLOAD});
});

test('Given a ADD_OPERATOR_SYNONYM_FULFILLED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.ADD_OPERATOR_SYNONYM_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, operators: PAYLOAD});
});

test('Given a REMOVE_OPERATOR_FULFILLED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.REMOVE_OPERATOR_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, operators: PAYLOAD});
});

test('Given a REMOVE_OPERATOR_SYNONYM_FULFILLED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.REMOVE_OPERATOR_SYNONYM_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, operators: PAYLOAD});
});

test('Given a FETCH_OPERATORS_REJECTED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.FETCH_OPERATORS_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a ADD_OPERATOR_REJECTED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.ADD_OPERATOR_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a ADD_OPERATOR_SYNONYM_REJECTED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.ADD_OPERATOR_SYNONYM_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a REMOVE_OPERATOR_REJECTED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.REMOVE_OPERATOR_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a REMOVE_OPERATOR_SYNONYM_REJECTED action, when reducing, then return the new state', () => {
    const newState = operatorsReducer(undefined, action(actions.REMOVE_OPERATOR_SYNONYM_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

function action(type) {
    return {type: type, payload: PAYLOAD};
}