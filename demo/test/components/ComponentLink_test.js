import React from 'react';
import {NavLink} from "reactstrap";
import ComponentLink from '../../src/components/ComponentLink';

describe('ComponentLink', () => {
    let wrapper, props;

    beforeEach(() => {
        props = {item: {id: 'someId', menuTitle: 'title'} , activeItem: 'someId', toBaseUrl: 'someurl/', onActiveChange : () => {} };
        wrapper = mount(<ComponentLink {...props} />);
    });

    it('has the correct class', () => {
        expect(wrapper).to.have.className('componentLink');
    });

    it('produces a link', () => {
        expect(wrapper.find('a')).to.have.className('nav-link');
    });
});

