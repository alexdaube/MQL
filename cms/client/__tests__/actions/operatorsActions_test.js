import td from 'testdouble';
import {operatorsClient} from '../../src/clients/clients';
import * as actions from '../../src/actions/operatorsActions';

const OPERATOR_NAME = 'a operator name';
const SYNONYM = 'a synonym';

let dispatch;

beforeEach(() => {
    td.reset();
    dispatch = td.function();
    td.replace(operatorsClient, 'fetchOperators');
    td.replace(operatorsClient, 'addOperator');
    td.replace(operatorsClient, 'removeOperator');
    td.replace(operatorsClient, 'addSynonym');
    td.replace(operatorsClient, 'removeSynonym');
});

test('When fetchOperators, then return a fetchOperators dispatcher', () => {
    actions.fetchOperators()(dispatch);
    td.verify(operatorsClient.fetchOperators());
    td.verify(dispatch({type: "FETCH_OPERATORS", payload: td.matchers.anything()}))
});

test('When addOperator, then return an addOperator dispatcher', () => {
    actions.addOperator(OPERATOR_NAME)(dispatch);
    td.verify(operatorsClient.addOperator({name: OPERATOR_NAME, synonyms: []}));
    td.verify(dispatch({type: "ADD_OPERATOR", payload: td.matchers.anything()}));
});

test('When removeOperator, then return a removeOperator dispatcher', () => {
    actions.removeOperator(OPERATOR_NAME)(dispatch);
    td.verify(operatorsClient.removeOperator(OPERATOR_NAME));
    td.verify(dispatch({type: "REMOVE_OPERATOR", payload: td.matchers.anything()}));
});

test('When addOperatorSynonym, then return an addOperatorSynonym dispatcher', () => {
    actions.addOperatorSynonym(OPERATOR_NAME, SYNONYM)(dispatch);
    td.verify(operatorsClient.addSynonym(OPERATOR_NAME, SYNONYM));
    td.verify(dispatch({type: "ADD_OPERATOR_SYNONYM", payload: td.matchers.anything()}));
});

test('When removeOperatorSynonym, then return a removeOperatorSynonym dispatcher', () => {
    actions.removeOperatorSynonym(OPERATOR_NAME, SYNONYM)(dispatch);
    td.verify(operatorsClient.removeSynonym(OPERATOR_NAME, SYNONYM));
    td.verify(dispatch({type: "REMOVE_OPERATOR_SYNONYM", payload: td.matchers.anything()}));
});