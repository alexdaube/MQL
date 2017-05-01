import React from 'react';
import {mount} from 'enzyme';
import td from 'testdouble';

import * as actions from '../../../src/actions/operatorsActions';
import OperatorsManager from '../../../src/views/apps/OperatorsManager';

const NAME = "A operator name";
const SYNONYM = "A operator synonym";

let operatorsManager, store, operators, operator;

beforeEach(() => {
    store = td.object();
    td.replace(actions, "fetchOperators");
    td.replace(actions, "addOperator");
    td.replace(actions, "addOperatorSynonym");
    td.replace(actions, "removeOperator");
    td.replace(actions, "removeOperatorSynonym");
    operator = {name: NAME, synonyms: [SYNONYM]};
    operators = [operator];
    td.when(store.getState()).thenReturn({operators: {operators: operators}});
    operatorsManager = mount(<OperatorsManager store={store} />);
});

test('It should contain a operator entry', () => {
    expect(operatorsManager.find(".operator-entry").length).toBe(1);
});

test('Given a operator, then it should contain a synonym entry', () => {
    expect(operatorsManager.find(".synonym-entry").length).toBe(1);
});

test('Given non operator, then it should not contain a synonym entry', () => {
    td.when(store.getState()).thenReturn({operators: {operators: []}});
    operatorsManager = mount(<OperatorsManager store={store} />);
    expect(operatorsManager.find(".synonym-entry").length).toBe(0);
});

test('When adding operator, then the addOperatorAction should be called', () => {
    const addKeyword = operatorsManager.find('KeywordEntry').filterWhere(k => k.hasClass('operator-entry')).props().addKeyword;
    addKeyword(NAME);
    td.verify(actions.addOperator(NAME));
});

test('When adding synonym, then the addOperatorSynonymAction should be called', () => {
    const addKeyword = operatorsManager.find('KeywordEntry').filterWhere(k => k.hasClass('synonym-entry')).props().addKeyword;
    addKeyword(SYNONYM);
    td.verify(actions.addOperatorSynonym(NAME, SYNONYM));
});

test('When removing operator, then the removeOperatorAction should be called', () => {
    const removeKeyword = operatorsManager.find('Keyword').filterWhere(k => k.hasClass('operator-name')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeOperator(NAME));
});

test('When removing synonym, then the removeOperatorSynonymAction should be called', () => {
    const removeKeyword = operatorsManager.find('Keyword').filterWhere(k => k.hasClass('operator-synonym')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeOperatorSynonym(NAME, SYNONYM));
});
