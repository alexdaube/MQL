import React from 'react';
import Header from '../../src/components/Header';

describe('Header', () => {
    let wrapper;

    beforeEach(() => {
        wrapper = mount(<Header/>);
    });

    it('mobile menu should not be open by default', () => {
        expect(wrapper.state().isOpen).to.be.false;
    });

    it('mobile menu toggles when button is clicked', () => {
        wrapper.find('button').simulate('click');
        expect(wrapper.state().isOpen).to.be.true;

        wrapper.find('button').simulate('click');
        expect(wrapper.state().isOpen).to.be.false;
    })
});
