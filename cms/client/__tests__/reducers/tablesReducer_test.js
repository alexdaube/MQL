import tablesReducer from '../../src/reducers/tablesReducer';
import * as actions from '../../src/actions/tablesActions';

const PAYLOAD = ["a payload"];

let state;

beforeEach(() => {
    state = {tables: [], fetching: false, error: null}; 
});

test('Given a FETCH_TABLES_PENDING action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.FETCH_TABLES_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a ADD_TABLES_PENDING action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_TABLE_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a ADD_TABLES_ATTRIBUTE_PENDING action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_TABLE_ATTRIBUTE_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a ADD_TABLES_SYNONYM_PENDING action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_ATTRIBUTE_SYNONYM_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a ADD_TABLES_FOREIGN_KEY_PENDING action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_TABLE_FOREIGN_KEY_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a REMOVE_TABLES_PENDING action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_TABLE_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a REMOVE_TABLES_ATTRIBUTE_PENDING action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_TABLE_ATTRIBUTE_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a REMOVE_TABLES_SYNONYM_PENDING action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_ATTRIBUTE_SYNONYM_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a REMOVE_TABLES_FOREIGN_KEY_PENDING action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_TABLE_FOREIGN_KEY_PENDING));
    expect(newState).toEqual({...state, fetching: true});
});

test('Given a FETCH_TABLES_FULFILLED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.FETCH_TABLES_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, tables: PAYLOAD});
});

test('Given a ADD_TABLES_FULFILLED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_TABLE_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, tables: PAYLOAD});
});

test('Given a ADD_TABLES_ATTRIBUTE_FULFILLED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_TABLE_ATTRIBUTE_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, tables: PAYLOAD});
});

test('Given a ADD_TABLES_SYNONYM_FULFILLED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_ATTRIBUTE_SYNONYM_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, tables: PAYLOAD});
});

test('Given a ADD_TABLES_FOREIGN_KEY_FULFILLED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_TABLE_FOREIGN_KEY_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, tables: PAYLOAD});
});

test('Given a REMOVE_TABLES_FULFILLED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_TABLE_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, tables: PAYLOAD});
});

test('Given a REMOVE_TABLES_ATTRIBUTE_FULFILLED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_TABLE_ATTRIBUTE_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, tables: PAYLOAD});
});

test('Given a REMOVE_TABLES_SYNONYM_FULFILLED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_ATTRIBUTE_SYNONYM_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, tables: PAYLOAD});
});

test('Given a REMOVE_TABLES_FOREIGN_KEY_FULFILLED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_TABLE_FOREIGN_KEY_FULFILLED));
    expect(newState).toEqual({...state, fetching: false, tables: PAYLOAD});
});

test('Given a FETCH_TABLES_REJECTED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.FETCH_TABLES_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a ADD_TABLES_REJECTED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_TABLE_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a ADD_TABLES_ATTRIBUTE_REJECTED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_TABLE_ATTRIBUTE_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a ADD_TABLES_SYNONYM_REJECTED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_ATTRIBUTE_SYNONYM_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a ADD_TABLES_FOREIGN_KEY_REJECTED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.ADD_TABLE_FOREIGN_KEY_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a REMOVE_TABLES_REJECTED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_TABLE_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a REMOVE_TABLES_ATTRIBUTE_REJECTED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_TABLE_ATTRIBUTE_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a REMOVE_TABLES_SYNONYM_REJECTED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_ATTRIBUTE_SYNONYM_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

test('Given a REMOVE_TABLES_FOREIGN_KEY_REJECTED action, when reducing, then return the new state', () => {
    const newState = tablesReducer(undefined, action(actions.REMOVE_TABLE_FOREIGN_KEY_REJECTED));
    expect(newState).toEqual({...state, fetching: false, error: PAYLOAD});
});

function action(type) {
    return {type: type, payload: PAYLOAD};
}