import React from "react";
import td from "testdouble";
import {SearchBar} from "../../src/containers/SearchBar";


describe('SearchBar', () => {
    const input = 'query';
    let wrapper, props, event;

    beforeEach(() => {
        event = {target: {value: input}, preventDefault: td.function('preventDefault')};
        props = {fetchQuery: td.function('fetchQuery')};
        wrapper = mount(<SearchBar {...props} />);
    });

    it('should have correct class', () => {
        expect(wrapper).to.have.className('mqlSearchBar');
    });

    it('state.term must be empty on init', () => {
        expect(wrapper.state().term).to.be.empty;
    });

    it('onInputChange should update state.term', () => {
        wrapper.instance().onInputChange(event);
        expect(wrapper.state().term).to.be.equal(input);
    });

    it('onFormSubmit should preventDefault and call fetchQuery', () => {
        wrapper.instance().onFormSubmit(event);
        td.verify(event.preventDefault());
        td.verify(props.fetchQuery(wrapper.state().term));
    });

    it('onFormSubmit should reset state.term after fetch', () => {
        wrapper.setState({term: input});
        wrapper.instance().onFormSubmit(event);
        td.verify(props.fetchQuery(input));
        expect(wrapper.state().term).to.be.empty;
    });

    it('button fires onFormSubmit', () => {
        wrapper.find('button').get(0).click();
        td.verify(props.fetchQuery(wrapper.state().term));
    });

    it('state.term should update when search input text changes', () => {
        wrapper.find('input').simulate('change', {target: {value: input}});
        expect(wrapper.state().term).to.be.equal(input);
    });
});

