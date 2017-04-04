import React from "react";
import td from "testdouble";
import moment from "moment";
import {SearchBar} from "../../src/containers/SearchBar";


describe('SearchBar', () => {
    const input = 'query';
    let wrapper, props, event, someSuggestion;

    beforeEach(() => {
        event = {target: {value: input}, preventDefault: td.function('preventDefault')};
        props = {
            fetchQuery: td.function('fetchQuery'),
            fetchSuggestions: td.function('fetchSuggestions'),
            clearSuggestions: td.function('clearSuggestions'),
            suggestions: {suggestions: [], error: '', isLoading: false}
        };
        wrapper = mount(<SearchBar {...props} />);
    });

    it('should have correct class', () => {
        expect(wrapper).to.have.className('mqlSearchBar');
    });

    it('state.value must be empty on init', () => {
        expect(wrapper.state().value).to.be.empty;
    });

    it('onChange should update state.value', () => {
        wrapper.instance().onChange(event, {newValue: input});
        expect(wrapper.state().value).to.be.equal(input);
    });

    const getSuggestionValueMethodTest = (type, suggestionToAppend) => {
        it(`should auto suggest with suggestion as ${type} inside input box`, () => {
            wrapper.setState({value: `${input} `});
            someSuggestion = {name: "name", type: type};

            const newInput = wrapper.instance().getSuggestionValue(someSuggestion);

            expect(newInput).to.be.eql(`${input} ${suggestionToAppend}`);
        });
    };

    getSuggestionValueMethodTest("VARCHAR", '"value"');
    getSuggestionValueMethodTest("DECIMAL", '0.0');
    getSuggestionValueMethodTest("INTEGER", '0');
    getSuggestionValueMethodTest("DATE", moment().format('YYYY-MM-DD'));
    getSuggestionValueMethodTest("NAME", 'name');

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

