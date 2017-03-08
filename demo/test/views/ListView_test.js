import React from 'react';
import Helmet from 'react-helmet';
import SearchBar from "../../src/containers/SearchBar";

import ListView from "../../src/views/ListView";


describe('ListView', () => {
    let wrapper;
    beforeEach(() => {
        wrapper = shallow(<ListView/>);
    });

    it('has the correct class', () => {
        expect(wrapper).to.have.className('mqlListView');
    });

    it('uses Helmet component', () => {
        expect(wrapper.find(Helmet)).to.have.length(1);
    });

    it('shows a search bar', () => {
        expect(wrapper.find(SearchBar)).to.have.length(1);
    });

    it('shows a dynamic list', () => {
        expect(wrapper.find(Helmet)).to.have.length(1);
    });
});

