import React from 'react';
import {shallow} from 'enzyme';
import {Link} from 'react-router';

import Layout from '../../../src/views/layouts/Layout';
import Menu from '../../../src/views/components/Menu';

let layout, child;

beforeEach(() => {
    child = <div className="unique">A child</div>;
    layout = shallow(<Layout>{child}</Layout>);
});

test('It should render the children', () => {
     expect(layout.contains(child)).toBe(true);
});

test('It should contain one menu', () => {
    expect(layout.find(Menu).length).toBe(1);
});

test('It should contain one keywords menu item', () => {
    expect(layout.contains(<Link to="/keywords"><h2>Keywords</h2></Link>)).toBe(true);
});

test('It should contain one database menu item', () => {
    expect(layout.contains(<Link to="/database"><h2>Database</h2></Link>)).toBe(true);
});
