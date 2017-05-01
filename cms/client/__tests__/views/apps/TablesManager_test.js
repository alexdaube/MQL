import React from 'react';
import {mount} from 'enzyme';
import td from 'testdouble';

import * as actions from '../../../src/actions/tablesActions';
import TablesManager from '../../../src/views/apps/TablesManager';
import KeywordEntry from '../../../src/views/components/KeywordEntry';

const TABLE_NAME = 'a table name';
const TABLE_SYNONYM = 'a table synonym';
const ATTRIBUTE_NAME = 'an attribute name';
const ATTRIBUTE_SYNONYM = 'an attribute synonym';
const FROM_ATTRIBUTE = 'a from attribute';
const TO_TABLE = 'a to table';
const TO_ATTRIBUTE = 'a to attribute';
let tablesManager, attribute, foreignKey, table, tables, store;

beforeEach(() => {
    store = td.object();
    td.replace(actions, 'fetchTables');
    td.replace(actions, 'addTable');
    td.replace(actions, 'addTableAttribute');
    td.replace(actions, 'addTableSynonym');
    td.replace(actions, 'addAttributeSynonym');
    td.replace(actions, 'removeTable');
    td.replace(actions, 'removeTableAttribute');
    td.replace(actions, 'removeTableSynonym');
    td.replace(actions, 'removeAttributeSynonym');
    attribute = {name: ATTRIBUTE_NAME, synonyms: [ATTRIBUTE_SYNONYM]};
    foreignKey = {fromAttribute: FROM_ATTRIBUTE, toTable: TO_TABLE, toAttribute: TO_ATTRIBUTE};
    table = {name: TABLE_NAME, synonyms: [TABLE_SYNONYM], attributes: [attribute], foreignKeys: [foreignKey]};
    tables = [table];
    td.when(store.getState()).thenReturn({tables: {tables: tables}});
    tablesManager = mount(<TablesManager store={store} />);
});

test('It should contain a table entry', () => {
    expect(tablesManager.find(".table-entry").length).toBe(1);
});

test('Given a table, then it should contain an attribute entry', () => {
    expect(tablesManager.find('.attribute-entry').length).toBe(1);
});

test('Given a table, then it should contain a synonym entry', () => {
    expect(tablesManager.find('.synonym-entry').length).toBe(1);
});

test('Given no table, then it should still contain a table entry', () => {
    expect(tablesManager.find(".table-entry").length).toBe(1);
});

test('Given no table, then it should not contain an attribute entry', () => {
    td.when(store.getState()).thenReturn({tables:{tables:[]}});
    tablesManager = mount(<TablesManager store={store} />);
    expect(tablesManager.find(".attribute-entry").length).toBe(0);
});

test('Given no table, then it should not contain a synonym entry', () => {
    td.when(store.getState()).thenReturn({tables:{tables:[]}});
    tablesManager = mount(<TablesManager store={store} />);
    expect(tablesManager.find('synonym-entry').length).toBe(0);
});

test('When adding table, then the addTableAction should be called', () => {
    const addKeyword = tablesManager.find('KeywordEntry').filterWhere(k => k.hasClass('table-entry')).props().addKeyword;
    addKeyword(TABLE_NAME);
    td.verify(actions.addTable(TABLE_NAME));
});

test('When adding attribute, then the addTableAttributeAction should be called', () => {
    const addKeyword = tablesManager.find('KeywordEntry').filterWhere(k => k.hasClass('attribute-entry')).props().addKeyword;
    addKeyword(ATTRIBUTE_NAME);
    td.verify(actions.addTableAttribute(TABLE_NAME, ATTRIBUTE_NAME));
});

test('When adding table synonym, then the addTableSynonymAction should be called', () => {
    const addKeyword = tablesManager.find('KeywordEntry').filterWhere(k => k.hasClass('synonym-entry')).props().addKeyword;
    addKeyword(TABLE_SYNONYM);
    td.verify(actions.addTableSynonym(TABLE_NAME, TABLE_SYNONYM))
});

test('When adding attribute synonym, then the addAttributeSynonymAction should be called', () => {
    const addKeyword = tablesManager.find('KeywordEntry').filterWhere(k => k.hasClass('attribute-synonym-entry')).props().addKeyword;
    addKeyword(ATTRIBUTE_SYNONYM);
    td.verify(actions.addAttributeSynonym(TABLE_NAME, ATTRIBUTE_NAME, ATTRIBUTE_SYNONYM));
});

test('When removing table, then the removeTableAction should be called', () => {
    const removeKeyword = tablesManager.find('Keyword').filterWhere(k => k.hasClass('table-name')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeTable(TABLE_NAME));
});

test('When removing attribute, then the removeTableAttributeAction should be called', () => {
    const removeKeyword = tablesManager.find('Keyword').filterWhere(k => k.hasClass('table-attribute')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeTableAttribute(TABLE_NAME, ATTRIBUTE_NAME));
});

test('When removing table synonym, then the removeTableSynonymAction should be called', () => {
    const removeKeyword = tablesManager.find('Keyword').filterWhere(k => k.hasClass('table-synonym')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeTableSynonym(TABLE_NAME, TABLE_SYNONYM));
});

test('When removing attribute synonym, then the removeAttributeSynonymAction should be called', () => {
    const removeKeyword = tablesManager.find('Keyword').filterWhere(k => k.hasClass('attribute-synonym')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeAttributeSynonym(TABLE_NAME, ATTRIBUTE_NAME, ATTRIBUTE_SYNONYM));
});