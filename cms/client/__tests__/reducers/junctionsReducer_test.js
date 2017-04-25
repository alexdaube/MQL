import junctionsReducer from "../../src/reducers/junctionsReducer";
import * as actions from "../../src/actions/junctionsActions";

const PAYLOAD = ["A payload"];

let state;

beforeEach(() => {
    state = {junctions: [], fetching: false, error: null};
});

test('Given a FETCH_JUNCTIONS_PENDING action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.FETCH_JUNCTIONS_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a ADD_JUNCTION_PENDING action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.ADD_JUNCTION_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a ADD_JUNCTION_SYNONYM_PENDING action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.ADD_JUNCTION_SYNONYM_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a REMOVE_JUNCTION_PENDING action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.REMOVE_JUNCTION_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a REMOVE_JUNCTION_SYNONYM_PENDING action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.REMOVE_JUNCTION_SYNONYM_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a FETCH_JUNCTIONS_FULFILLED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.FETCH_JUNCTIONS_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, junctions: PAYLOAD});
});

test('Given a ADD_JUNCTION_FULFILLED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.ADD_JUNCTION_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, junctions: PAYLOAD});
});

test('Given a ADD_JUNCTION_SYNONYM_FULFILLED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.ADD_JUNCTION_SYNONYM_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, junctions: PAYLOAD});
});

test('Given a REMOVE_JUNCTION_FULFILLED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.REMOVE_JUNCTION_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, junctions: PAYLOAD});
});

test('Given a REMOVE_JUNCTION_SYNONYM_FULFILLED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.REMOVE_JUNCTION_SYNONYM_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, junctions: PAYLOAD});
});

test('Given a FETCH_JUNCTIONS_REJECTED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.FETCH_JUNCTIONS_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a ADD_JUNCTION_REJECTED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.ADD_JUNCTION_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a ADD_JUNCTION_SYNONYM_REJECTED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.ADD_JUNCTION_SYNONYM_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a REMOVE_JUNCTION_REJECTED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.REMOVE_JUNCTION_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a REMOVE_JUNCTION_SYNONYM_REJECTED action, when reducing, then return the new state', () => {
    const newState = junctionsReducer(undefined, action(actions.REMOVE_JUNCTION_SYNONYM_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

function action(type) {
    return {type: type, payload: PAYLOAD};
}