import React from 'react';
import {shallow} from 'enzyme';
import td from 'testdouble';

import Keyword from '../../../src/views/components/Keyword';

const NAME = 'A name';

let keyword, removeKeyword, child;

beforeEach(() => {
    removeKeyword = td.function();
    child = <div>A child</div>;
    keyword = shallow(<Keyword name={NAME} removeKeyword={removeKeyword}>{child}</Keyword>)
});

test('It should contains the name as a label', () => {
    expect(keyword.find('.keyword-name').text()).toBe(NAME);
});

test('Given a click on the remove button, then call the remove function', () => {
    keyword.find('a.btn').simulate('click');
    td.verify(removeKeyword());
});

test('Given a closed keyword, when clicking on it, then expand it', () => {
    expect(keyword.find('.keyword-children.keyword-active').length).toBe(0);
    keyword.find('.keyword-button').simulate('click');
    expect(keyword.find('.keyword-children.keyword-active').length).toBe(1);
});

test('Given a closed keyword, when clicking on it, then change the icon from plus to minus', () => {
    expect(keyword.find('.glyphicon-plus').length).toBe(1);
    expect(keyword.find('.glyphicon-minus').length).toBe(0);
    keyword.find('.keyword-button').simulate('click');
    expect(keyword.find('.glyphicon-plus').length).toBe(0);
    expect(keyword.find('.glyphicon-minus').length).toBe(1);
});

test('Given an open keyword, when clicking on it, then close it', () => {
    keyword.find('.keyword-button').simulate('click');
    keyword.find('.keyword-button').simulate('click');
    expect(keyword.state().active).toBe(false);
});

test('Given an open keyword, when clicking on it, then change the icon from minus to plus', () => {
    keyword.find('.keyword-button').simulate('click');
    keyword.find('.keyword-button').simulate('click');
    expect(keyword.find('.glyphicon-plus').length).toBe(1);
    expect(keyword.find('.glyphicon-minus').length).toBe(0);
});

test('Given no children, then the plus/minus icon is hidden', () => {
    keyword = shallow(<Keyword name={NAME} removeKeyword={removeKeyword} />);
    expect(keyword.find('.glyphicon').length).toBe(0);
});

