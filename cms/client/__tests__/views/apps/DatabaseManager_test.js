import React from 'react';
import {mount} from 'enzyme';
import td from 'testdouble';
import * as actions from '../../../src/actions/tablesActions';

import DatabaseManager from '../../../src/views/apps/DatabaseManager';
import KeywordEntry from '../../../src/views/components/KeywordEntry';

const TABLE_NAME = 'a table name';
const ATTRIBUTE_NAME = 'an attribute name';
const FROM_ATTRIBUTE = 'a from attribute';
const TO_TABLE = 'a to table';
const TO_ATTRIBUTE = 'a to attribute';
let databaseManager, attribute, foreignKey, table, tables, store;

beforeEach(() => {
    store = td.object();
    td.replace(actions, 'fetchTables');
    td.replace(actions, 'addTable');
    td.replace(actions, 'addTableAttribute');
    td.replace(actions, 'addTableForeignKey');
    td.replace(actions, 'removeTable');
    td.replace(actions, 'removeTableAttribute');
    td.replace(actions, 'removeTableForeignKey');
    attribute = {name: ATTRIBUTE_NAME};
    foreignKey = {fromAttribute: FROM_ATTRIBUTE, toTable: TO_TABLE, toAttribute: TO_ATTRIBUTE};
    table = {name: TABLE_NAME, synonyms: [], attributes: [attribute], foreignKeys: [foreignKey]};
    tables = [table];
    td.when(store.getState()).thenReturn({tables: {tables: tables}});
    databaseManager = mount(<DatabaseManager store={store} />);
});

test('It should contain a table entry', () => {
    expect(databaseManager.find(".table-entry").length).toBe(1);
});

test('Given a table, then it should contain an attribute entry', () => {
    expect(databaseManager.find('.attribute-entry').length).toBe(1);
});

test('Given a table, then it should contain a foreign key entry', () => {
    expect(databaseManager.find('ForeignKeyEntry').length).toBe(1);
});

test('Given no table, then it should still contain a table entry', () => {
    expect(databaseManager.find(".table-entry").length).toBe(1);
});

test('Given no table, then it should not contain an attribute entry', () => {
    td.when(store.getState()).thenReturn({tables:{tables:[]}});
    databaseManager = mount(<DatabaseManager store={store} />);
    expect(databaseManager.find(".attribute-entry").length).toBe(0);
});

test('Given no table, then it should not contain a foreign key entry', () => {
    td.when(store.getState()).thenReturn({tables:{tables:[]}});
    databaseManager = mount(<DatabaseManager store={store} />);
    expect(databaseManager.find('ForeignKeyEntry').length).toBe(0);
});

test('When adding table, then the addTableAction should be called', () => {
    const addKeyword = databaseManager.find('KeywordEntry').filterWhere(k => k.hasClass('table-entry')).props().addKeyword;
    addKeyword(TABLE_NAME);
    td.verify(actions.addTable(TABLE_NAME));
});

test('When adding attribute, then the addTableAttributeAction should be called', () => {
    const addKeyword = databaseManager.find('KeywordEntry').filterWhere(k => k.hasClass('attribute-entry')).props().addKeyword;
    addKeyword(ATTRIBUTE_NAME);
    td.verify(actions.addTableAttribute(TABLE_NAME, ATTRIBUTE_NAME));
});

test('When adding foreign key, then the addTableForeignKeyAction should be called', () => {
    const addKeyword = databaseManager.find('ForeignKeyEntry').props().addKeyword;
    addKeyword(FROM_ATTRIBUTE, TO_TABLE, TO_ATTRIBUTE);
    td.verify(actions.addTableForeignKey(TABLE_NAME, FROM_ATTRIBUTE, TO_TABLE, TO_ATTRIBUTE))
});

test('When removing table, then the removeTableAction should be called', () => {
    const removeKeyword = databaseManager.find('Keyword').filterWhere(k => k.hasClass('database-table')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeTable(TABLE_NAME));
});

test('When removing attribute, then the removeTableAttributeAction should be called', () => {
    const removeKeyword = databaseManager.find('Keyword').filterWhere(k => k.hasClass('database-attribute')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeTableAttribute(TABLE_NAME, ATTRIBUTE_NAME));
});

test('When removing foreign key, then the removeTableForeignKeyAction should be called', () => {
    const removeKeyword = databaseManager.find('Keyword').filterWhere(k => k.hasClass('database-foreignKey')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeTableForeignKey(TABLE_NAME, FROM_ATTRIBUTE, TO_TABLE, TO_ATTRIBUTE));
});