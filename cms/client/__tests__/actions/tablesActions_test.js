import td from 'testdouble';
import {tablesClient} from '../../src/clients/clients';
import * as actions from '../../src/actions/tablesActions';

const TABLE_NAME = 'a table name';
const SYNONYM = 'a synonym';
const FROM_TABLE = 'a from table name';
const FROM_ATTRIBUTE = 'a from attribute name';
const TO_TABLE = 'a to table name';
const TO_ATTRIBUTE = 'a to attribute name';
const ATTRIBUTE_NAME = 'an attribute name';

let dispatch;

beforeEach(() => {
    td.reset();
    dispatch = td.function();
    td.replace(tablesClient, 'fetchTables');
    td.replace(tablesClient, 'addTable');
    td.replace(tablesClient, 'removeTable');
    td.replace(tablesClient, 'addSynonym');
    td.replace(tablesClient, 'removeSynonym');
    td.replace(tablesClient, 'addForeignKey');
    td.replace(tablesClient, 'removeForeignKey');
    td.replace(tablesClient, 'addAttribute');
    td.replace(tablesClient, 'removeAttribute');
    td.replace(tablesClient, 'addAttributeSynonym');
    td.replace(tablesClient, 'removeAttributeSynonym');
});

test('When fetchTables, then return a fetchTables dispatcher', () => {
    actions.fetchTables()(dispatch);
    td.verify(tablesClient.fetchTables());
    td.verify(dispatch({type: "FETCH_TABLES", payload: td.matchers.anything()}))
});

test('When addTable, then return an addTable dispatcher', () => {
    actions.addTable(TABLE_NAME)(dispatch);
    td.verify(tablesClient.addTable({name: TABLE_NAME, attributes:[], synonyms: [], foreignKeys: []}));
    td.verify(dispatch({type: "ADD_TABLE", payload: td.matchers.anything()}));
});

test('When removeTable, then return a removeTable dispatcher', () => {
    actions.removeTable(TABLE_NAME)(dispatch);
    td.verify(tablesClient.removeTable(TABLE_NAME));
    td.verify(dispatch({type: "REMOVE_TABLE", payload: td.matchers.anything()}));
});

test('When addTableSynonym, then return an addTableSynonym dispatcher', () => {
    actions.addTableSynonym(TABLE_NAME, SYNONYM)(dispatch);
    td.verify(tablesClient.addSynonym(TABLE_NAME, SYNONYM));
    td.verify(dispatch({type: "ADD_TABLE_SYNONYM", payload: td.matchers.anything()}));
});

test('When removeTableSynonym, then return a removeTableSynonym dispatcher', () => {
    actions.removeTableSynonym(TABLE_NAME, SYNONYM)(dispatch);
    td.verify(tablesClient.removeSynonym(TABLE_NAME, SYNONYM));
    td.verify(dispatch({type: "REMOVE_TABLE_SYNONYM", payload: td.matchers.anything()}));
});

test('When addTableForeignKey, then return a addTableForeignKey dispatcher', () => {
    actions.addTableForeignKey(FROM_TABLE, FROM_ATTRIBUTE, TO_TABLE, TO_ATTRIBUTE)(dispatch);
    td.verify(tablesClient.addForeignKey(FROM_TABLE, {fromAttribute: FROM_ATTRIBUTE, toTable: TO_TABLE, toAttribute: TO_ATTRIBUTE}));
    td.verify(dispatch({type: "ADD_TABLE_FOREIGN_KEY", payload: td.matchers.anything()}));
});

test('When removeTableForeignKey, then return a removeTableForeignKey dispatcher', () => {
    actions.removeTableForeignKey(FROM_TABLE, FROM_ATTRIBUTE, TO_TABLE, TO_ATTRIBUTE)(dispatch);
    td.verify(tablesClient.removeForeignKey(FROM_TABLE, {fromAttribute: FROM_ATTRIBUTE, toTable: TO_TABLE, toAttribute: TO_ATTRIBUTE}));
    td.verify(dispatch({type: "REMOVE_TABLE_FOREIGN_KEY", payload: td.matchers.anything()}));
});

test('When addTableAttribute, then return a addTableAttribute dispatcher', () => {
    actions.addTableAttribute(TABLE_NAME, ATTRIBUTE_NAME)(dispatch);
    td.verify(tablesClient.addAttribute(TABLE_NAME, {name: ATTRIBUTE_NAME, synonyms: []}));
    td.verify(dispatch({type: "ADD_TABLE_ATTRIBUTE", payload: td.matchers.anything()}));
});

test('When removeTableAttribute, then return a removeTableAttribute dispatcher', () => {
    actions.removeTableAttribute(TABLE_NAME, ATTRIBUTE_NAME)(dispatch);
    td.verify(tablesClient.removeAttribute(TABLE_NAME, ATTRIBUTE_NAME));
    td.verify(dispatch({type: "REMOVE_TABLE_ATTRIBUTE", payload: td.matchers.anything()}));
});

test('When addAttributeSynonym, then return a addAttributeSynonym dispatcher', () => {
    actions.addAttributeSynonym(TABLE_NAME, ATTRIBUTE_NAME, SYNONYM)(dispatch);
    td.verify(tablesClient.addAttributeSynonym(TABLE_NAME, ATTRIBUTE_NAME, SYNONYM));
    td.verify(dispatch({type: "ADD_ATTRIBUTE_SYNONYM", payload: td.matchers.anything()}));
});

test('When removeTableAttributeSynonym, then return a removeTableAttributeSynonym dispatcher', () => {
    actions.removeAttributeSynonym(TABLE_NAME, ATTRIBUTE_NAME, SYNONYM)(dispatch);
    td.verify(tablesClient.removeAttributeSynonym(TABLE_NAME, ATTRIBUTE_NAME, SYNONYM));
    td.verify(dispatch({type: "REMOVE_ATTRIBUTE_SYNONYM", payload: td.matchers.anything()}));
});
