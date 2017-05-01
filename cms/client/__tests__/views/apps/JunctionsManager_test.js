import React from 'react';
import {mount} from 'enzyme';
import td from 'testdouble';

import * as actions from '../../../src/actions/junctionsActions';
import JunctionsManager from '../../../src/views/apps/JunctionsManager';

const NAME = "A junction name";
const SYNONYM = "A junction synonym";

let junctionsManager, store, junctions, junction;

beforeEach(() => {
    store = td.object();
    td.replace(actions, "fetchJunctions");
    td.replace(actions, "addJunction");
    td.replace(actions, "addJunctionSynonym");
    td.replace(actions, "removeJunction");
    td.replace(actions, "removeJunctionSynonym");
    junction = {name: NAME, synonyms: [SYNONYM]};
    junctions = [junction];
    td.when(store.getState()).thenReturn({junctions: {junctions: junctions}});
    junctionsManager = mount(<JunctionsManager store={store} />);
});

test('It should contain a junction entry', () => {
    expect(junctionsManager.find(".junction-entry").length).toBe(1);
});

test('Given a junction, then it should contain a synonym entry', () => {
    expect(junctionsManager.find(".synonym-entry").length).toBe(1);
});

test('Given non junction, then it should not contain a synonym entry', () => {
    td.when(store.getState()).thenReturn({junctions: {junctions: []}});
    junctionsManager = mount(<JunctionsManager store={store} />);
    expect(junctionsManager.find(".synonym-entry").length).toBe(0);
});

test('When adding junction, then the addJunctionAction should be called', () => {
    const addKeyword = junctionsManager.find('KeywordEntry').filterWhere(k => k.hasClass('junction-entry')).props().addKeyword;
    addKeyword(NAME);
    td.verify(actions.addJunction(NAME));
});

test('When adding synonym, then the addJunctionSynonymAction should be called', () => {
    const addKeyword = junctionsManager.find('KeywordEntry').filterWhere(k => k.hasClass('synonym-entry')).props().addKeyword;
    addKeyword(SYNONYM);
    td.verify(actions.addJunctionSynonym(NAME, SYNONYM));
});

test('When removing junction, then the removeJunctionAction should be called', () => {
    const removeKeyword = junctionsManager.find('Keyword').filterWhere(k => k.hasClass('junction-name')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeJunction(NAME));
});

test('When removing synonym, then the removeJunctionSynonymAction should be called', () => {
    const removeKeyword = junctionsManager.find('Keyword').filterWhere(k => k.hasClass('junction-synonym')).props().removeKeyword;
    removeKeyword();
    td.verify(actions.removeJunctionSynonym(NAME, SYNONYM));
});