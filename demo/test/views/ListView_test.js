import React from "react";
import Helmet from "react-helmet";
import {Alert} from "reactstrap";
import SearchBar from "../../src/containers/SearchBar";
import DynamicList from "../../src/containers/DynamicList";
import QueryKeywordsLegend from "../../src/components/QueryKeywordsLegend";
import {ListView} from "../../src/views/ListView";


describe('ListView', () => {
    let wrapper, props;
    beforeEach(() => {
        props = {query: {error: ''}};
        wrapper = shallow(<ListView {...props}/>);
    });

    it('has the correct class', () => {
        expect(wrapper).to.have.className('mqlListView');
    });

    it('uses Helmet component', () => {
        expect(wrapper.find(Helmet)).to.have.length(1);
    });

    it('shows a query keywords legend', () => {
        expect(wrapper.find(QueryKeywordsLegend)).to.have.length(1);
    });

    it('shows a search bar', () => {
        expect(wrapper.find(SearchBar)).to.have.length(1);
    });

    it('shows a dynamic list', () => {
        expect(wrapper.find(DynamicList)).to.have.length(1);
    });

    it('shows an error message', () => {
        props.query.error = 'some error';
        wrapper = shallow(<ListView {...props}/>);
        expect(wrapper.find(Alert)).to.have.length(1);
    });

    it('does not show an error message by default', () => {
        expect(wrapper.find(Alert)).to.have.length(0);
    });
});

