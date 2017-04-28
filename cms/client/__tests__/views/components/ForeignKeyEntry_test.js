import React from 'react';
import {shallow} from 'enzyme';
import td from 'testdouble';

import ForeignKeyEntry from '../../../src/views/components/ForeignKeyEntry';

const FROM_ATTRIBUTE_NAME = 'a from attribute name';
const TO_TABLE_NAME = 'a to table name';
const TO_ATTRIBUTE_NAME = 'a to attribute name';

let foreignKeyEntry, from_table, tables, addKeyword, preventDefault;

beforeEach(() => {
    addKeyword = td.function();
    preventDefault = td.function();
    from_table = { name: 'a table name', synonyms: [], attributes: [], foreignKeys: [] };
    tables = [from_table];
    foreignKeyEntry = shallow(<ForeignKeyEntry fromTable={from_table} tables={tables} addKeyword={addKeyword} />);
});

test('Given a from attribute, then the from attribute should change', () => {
    foreignKeyEntry.find('.foreign-key-from-attribute').simulate('change', {value: FROM_ATTRIBUTE_NAME});
    expect(foreignKeyEntry.state().fromAttribute).toBe(FROM_ATTRIBUTE_NAME);
});

test('Given a to table, then to table should change', () => {
    foreignKeyEntry.find('.foreign-key-to-table').simulate('change', {value: TO_TABLE_NAME});
    expect(foreignKeyEntry.state().toTable).toBe(TO_TABLE_NAME);
});

test('Given a to attribute, then the to attribute should change', () => {
    foreignKeyEntry.find('.foreign-key-to-attribute').simulate('change', {value: TO_ATTRIBUTE_NAME});
    expect(foreignKeyEntry.state().toAttribute).toBe(TO_ATTRIBUTE_NAME);
});

test('Given valid information, when submitting, then call the addKeyword function', () => {
    foreignKeyEntry.find('.foreign-key-from-attribute').simulate('change', {value: FROM_ATTRIBUTE_NAME});
    foreignKeyEntry.find('.foreign-key-to-table').simulate('change', {value: TO_TABLE_NAME});
    foreignKeyEntry.find('.foreign-key-to-attribute').simulate('change', {value: TO_ATTRIBUTE_NAME});
    foreignKeyEntry.find('form').simulate('submit', {preventDefault: preventDefault});
    td.verify(addKeyword(FROM_ATTRIBUTE_NAME, TO_TABLE_NAME, TO_ATTRIBUTE_NAME));
});

test('Given valid information, when submitting, then prevent event default', () => {
    foreignKeyEntry.find('form').simulate('submit', {preventDefault: preventDefault});
    td.verify(preventDefault());
});

test('Given no from attribute, when submitting, then do not call the addKeyword function', () => {
    foreignKeyEntry.find('.foreign-key-to-table').simulate('change', {value: TO_TABLE_NAME});
    foreignKeyEntry.find('.foreign-key-to-attribute').simulate('change', {value: TO_ATTRIBUTE_NAME});
    foreignKeyEntry.find('form').simulate('submit', {preventDefault: preventDefault});
    td.verify(addKeyword(), {times: 0, ignoreExtraArgs: true});
});

test('Given no to table, when submitting, then do not call the addKeyword function', () => {
    foreignKeyEntry.find('.foreign-key-from-attribute').simulate('change', {value: FROM_ATTRIBUTE_NAME});
    foreignKeyEntry.find('.foreign-key-to-attribute').simulate('change', {value: TO_ATTRIBUTE_NAME});
    foreignKeyEntry.find('form').simulate('submit', {preventDefault: preventDefault});
    td.verify(addKeyword(), {times: 0, ignoreExtraArgs: true});
});


test('Given no to attribute, when submitting, then do not call the addKeyword function', () => {
    foreignKeyEntry.find('.foreign-key-from-attribute').simulate('change', {value: FROM_ATTRIBUTE_NAME});
    foreignKeyEntry.find('.foreign-key-to-table').simulate('change', {value: TO_TABLE_NAME});
    foreignKeyEntry.find('form').simulate('submit', {preventDefault: preventDefault});
    td.verify(addKeyword(), {times: 0, ignoreExtraArgs: true});
});

