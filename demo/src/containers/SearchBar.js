import React, {Component} from "react";
import Autosuggest from 'react-autosuggest';
import {Button, Form, FormGroup, Input, InputGroupButton, InputGroup} from "reactstrap";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import * as actions from "../actions";


const theme = {
    container: 'searchBarContainer',
    input: 'searchBarInput form-control'
};

const languages = [
    {
        name: 'C',
        year: 1972
    },
    {
        name: 'Elm',
        year: 2012
    }
];



const renderSectionTitle = (section) => {
    debugger;
    return (
        <strong>{section.title}</strong>
    );
};


const getSectionSuggestions = (section) => {
    debugger;
    if(section.hints) {
        return section;
    }
    return section.suggestions;
};


// Teach Autosuggest how to calculate suggestions for any given input value.
// const getSuggestions = value => {
//     const inputValue = value.trim().toLowerCase();
//     const inputLength = inputValue.length;
//
//     return inputLength === 0 ? [] : languages.filter(lang =>
//             lang.name.toLowerCase().slice(0, inputLength) === inputValue
//         );
// };

// When suggestion is clicked, Autosuggest needs to populate the input element
// based on the clicked suggestion. Teach Autosuggest how to calculate the
// input value for every given suggestion.
const getSuggestionValue = suggestion => suggestion.name;

// Use your imagination to render suggestions.
const renderSuggestion = suggestion => {
    debugger;
    const suggestionText = suggestion.hints ? suggestion.hints : `${suggestion.name} with type: ${suggestion.type}`;
    return (
        <div>
            {suggestionText}
        </div>
    );
};



export class SearchBar extends Component {
    // Autosuggest is a controlled component.
    // This means that you need to provide an input value
    // and an onChange handler that updates this value (see below).
    // Suggestions also need to be provided to the Autosuggest,
    // and they are initially empty because the Autosuggest is closed.
    constructor(props) {
        super(props);
        this.state = {
            value: '',
            suggestions: []
        };
        this.onInputChange = this.onInputChange.bind(this);
        this.onFormSubmit = this.onFormSubmit.bind(this);
    }

    onChange = (event, { newValue }) => {
        this.setState({
            value: newValue
        });
    };

    onInputChange(event) {
        this.setState({
            value: event.target.value
        });
    }

    onFormSubmit(event) {
        event.preventDefault();
        this.props.fetchQuery(this.state.value);
    }

    // Autosuggest will call this function every time you need to update suggestions.
    // You already implemented this logic above, so just use it.
    onSuggestionsFetchRequested = ({ value }) => {
        this.setState({
            suggestions: getSuggestions(value)
        });
    };

    // Autosuggest will call this function every time you need to clear suggestions.
    onSuggestionsClearRequested = () => {
        this.setState({
            suggestions: []
        });
    };

    render() {
        const { value } = this.state;
        const { suggestions, fetchSuggestions, clearSuggestions } = this.props;

        const inputProps = {
            placeholder: 'Basic query format is Keyword + Operator + Value',
            value,
            onChange: this.onChange
        };

        return (
            <Form onSubmit={this.onFormSubmit} className="mqlSearchBar">
                <FormGroup>
                    <InputGroup>
                        <Autosuggest suggestions={suggestions.suggestions}
                                     onSuggestionsFetchRequested={fetchSuggestions}
                                     onSuggestionsClearRequested={clearSuggestions}
                                     getSuggestionValue={getSuggestionValue}
                                     renderSuggestion={renderSuggestion}
                                     inputProps={inputProps}
                                     theme={theme}
                                     multiSection={true}
                                     renderSectionTitle={renderSectionTitle}
                                     getSectionSuggestions={getSectionSuggestions}

                        />
                        <InputGroupButton className="searchBarButton">
                            <Button color="success">Submit</Button>
                        </InputGroupButton>
                    </InputGroup>
                </FormGroup>
            </Form>
        );
    }

}

const mapStateToProps = ({suggestions}) => {
    return {suggestions};
};

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({
        fetchQuery: actions.fetchQuery,
        fetchSuggestions: actions.fetchSuggestions,
        clearSuggestions: actions.clearSuggestions
    }, dispatch);
};


export default connect(mapStateToProps, mapDispatchToProps)(SearchBar)