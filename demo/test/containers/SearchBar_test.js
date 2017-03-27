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

    it('state.value must be empty on init', () => {
        expect(wrapper.state().value).to.be.empty;
    });

    it('onInputChange should update state.value', () => {
        wrapper.instance().onInputChange(event);
        expect(wrapper.state().value).to.be.equal(input);
    });

    it('onFormSubmit should preventDefault and call fetchQuery', () => {
        wrapper.instance().onFormSubmit(event);
        td.verify(event.preventDefault());
        td.verify(props.fetchQuery(wrapper.state().value));
    });

    it('button fires onFormSubmit', () => {
        wrapper.find('button').get(0).click();
        td.verify(props.fetchQuery(wrapper.state().value));
    });

    it('state.value should update when search input text changes', () => {
        wrapper.find('input').simulate('change', {target: {value: input}});
        expect(wrapper.state().value).to.be.equal(input);
    });
});

