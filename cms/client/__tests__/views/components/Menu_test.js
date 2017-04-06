import React from 'react';
import {shallow} from 'enzyme';

import Menu from '../../../src/views/components/Menu';

const TOP = '.nav-tabs';
const VERTICAL = '.nav-stacked';
const ACTIVE = '.active';
const INACTIVE = '.inactive';
const CHILD_ID = 0;

let menu, child;

beforeEach(() => {
    child = <div>A child</div>;
    menu = shallow(<Menu>{child}</Menu>);
});

test('Given top equals to true, then the menu should be rendered horizontal', () => {
    menu = shallow(<Menu top={true}>{child}</Menu>);
    expect(menu.find(TOP).length).toBe(1);
    expect(menu.find(VERTICAL).length).toBe(0);
});

test('Given top equals to false, then the menu should be rendered vertically', () => {
    expect(menu.find(TOP).length).toBe(0);
    expect(menu.find(VERTICAL).length).toBe(1);
});

test('Given no active item, then no items should be active', () => {
    expect(menu.find(ACTIVE).length).toBe(0);
    expect(menu.find(INACTIVE).length).toBe(1);
});

test('Given an ID, when activeItem, then the item is active', () => {
    menu.instance().activeItem(CHILD_ID);
    expect(menu.find(ACTIVE).length).toBe(1);
    expect(menu.find(INACTIVE).length).toBe(0);
});
