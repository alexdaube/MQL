import React from 'react'
import {App} from "../../src/views/App";
import Header from "../../src/components/Header";


describe('App', () => {
    it('shows a header', () => {
        const wrapper = shallow(<App/>);
        expect(wrapper.find(Header)).to.have.length(1);
    });
});