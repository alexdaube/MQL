import td from 'testdouble';
import {junctionsClient} from '../../src/clients/clients';
import * as actions from '../../src/actions/junctionsActions';

const JUNCTION_NAME = 'a junction name';
const SYNONYM = 'a synonym';

let dispatch;

beforeEach(() => {
    td.reset();
    dispatch = td.function();
    td.replace(junctionsClient, 'fetchJunctions');
    td.replace(junctionsClient, 'addJunction');
    td.replace(junctionsClient, 'removeJunction');
    td.replace(junctionsClient, 'addSynonym');
    td.replace(junctionsClient, 'removeSynonym');
});

test('When fetchJunctions, then return a fetchJunctions dispatcher', () => {
    actions.fetchJunctions()(dispatch);
    td.verify(junctionsClient.fetchJunctions());
    td.verify(dispatch({type: "FETCH_JUNCTIONS", payload: td.matchers.anything()}))
});

test('When addJunction, then return an addJunction dispatcher', () => {
    actions.addJunction(JUNCTION_NAME)(dispatch);
    td.verify(junctionsClient.addJunction({name: JUNCTION_NAME, synonyms: []}));
    td.verify(dispatch({type: "ADD_JUNCTION", payload: td.matchers.anything()}));
});

test('When removeJunction, then return a removeJunction dispatcher', () => {
    actions.removeJunction(JUNCTION_NAME)(dispatch);
    td.verify(junctionsClient.removeJunction(JUNCTION_NAME));
    td.verify(dispatch({type: "REMOVE_JUNCTION", payload: td.matchers.anything()}));
});

test('When addJunctionSynonym, then return an addJunctionSynonym dispatcher', () => {
    actions.addJunctionSynonym(JUNCTION_NAME, SYNONYM)(dispatch);
    td.verify(junctionsClient.addSynonym(JUNCTION_NAME, SYNONYM));
    td.verify(dispatch({type: "ADD_JUNCTION_SYNONYM", payload: td.matchers.anything()}));
});

test('When removeJunctionSynonym, then return a removeJunctionSynonym dispatcher', () => {
    actions.removeJunctionSynonym(JUNCTION_NAME, SYNONYM)(dispatch);
    td.verify(junctionsClient.removeSynonym(JUNCTION_NAME, SYNONYM));
    td.verify(dispatch({type: "REMOVE_JUNCTION_SYNONYM", payload: td.matchers.anything()}));
});