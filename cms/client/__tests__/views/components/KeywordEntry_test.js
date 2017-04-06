import React from 'react';
import {shallow} from 'enzyme';
import td from 'testdouble';

import KeywordEntry from '../../../src/views/components/KeywordEntry';

const NAME = 'a name';
const EMPTY = "";

let keywordEntry, addKeyword, preventDefault;

beforeEach(() => {
    addKeyword = td.function();
    preventDefault = td.function();
    keywordEntry = shallow(<KeywordEntry addKeyword={addKeyword} />)
});

test('Given entries, then the name should changes', () => {
    keywordEntry.find('input.form-control').simulate('change', {target: {value: NAME}, preventDefault: preventDefault});
    expect(keywordEntry.state().name).toBe(NAME);
});

test('Given entries, then prevent event default', () => {
    keywordEntry.find('input.form-control').simulate('change', {target: {value: NAME}, preventDefault: preventDefault});
    td.verify(preventDefault());
});

test('Given entries, when submitting, then call the addKeyword function with the name', () => {
    keywordEntry.find('input.form-control').simulate('change', {target: {value: NAME}, preventDefault: preventDefault});
    keywordEntry.find('form').simulate('submit', {preventDefault: preventDefault});
    td.verify(addKeyword(NAME));
});

test('Given entries, when submitting, then prevent event default', () => {
    keywordEntry.find('form').simulate('submit', {preventDefault: preventDefault});
    td.verify(preventDefault());
});

test('Given entries, when submitting, then reset name to empty', () => {
    keywordEntry.find('input.form-control').simulate('change', {target: {value: NAME}, preventDefault: preventDefault});
    keywordEntry.find('form').simulate('submit', {preventDefault: preventDefault});
    expect(keywordEntry.state().name).toBe(EMPTY);
});