import React, {Component} from "react";
import Autosuggest from 'react-autosuggest';
import {Button, Form, FormGroup, InputGroupButton, InputGroup, Badge} from "reactstrap";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import * as actions from "../actions";
import {getQueryKeywordBadgeDetails} from "../utils/badge";


const theme = {
    container: 'searchBarContainer', // 'react-autosuggest__container'
    containerOpen: 'react-autosuggest__container--open',
    input: 'searchBarInput form-control', // 'react-autosuggest__input'
    inputOpen:                'react-autosuggest__input--open',
    inputFocused:             'react-autosuggest__input--focused',
    suggestionsContainer:     'react-autosuggest__suggestions-container',
    suggestionsContainerOpen: 'react-autosuggest__suggestions-container--open',
    suggestionsList:          'react-autosuggest__suggestions-list',
    suggestion:               'react-autosuggest__suggestion',
    suggestionFirst:          'react-autosuggest__suggestion--first',
    suggestionHighlighted:    'react-autosuggest__suggestion--highlighted',
    sectionContainer:         'react-autosuggest__section-container',
    sectionContainerFirst:    'react-autosuggest__section-container--first',
    sectionTitle:             'react-autosuggest__section-title'
};

const renderSectionTitle = (section) => {
    return (
        <strong>{section.title}</strong>
    );
};


const getSectionSuggestions = (section) => {
    return section.suggestions;
};


const renderSuggestion = suggestion => {
    let suggestionType = getQueryKeywordBadgeDetails(suggestion.type);
    return (
        <div>
            <Badge color={suggestionType.color}>&nbsp;{suggestionType.letter}&nbsp;</Badge>&nbsp;
            {`${suggestion.name}`}
        </div>
    );
};

export class SearchBar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value: '',
            suggestions: []
        };
        this.onInputChange = this.onInputChange.bind(this);
        this.onFormSubmit = this.onFormSubmit.bind(this);
        this.getSuggestionValue = this.getSuggestionValue.bind(this);
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

    getSuggestionValue(suggestion) {
        const lastIndex = this.state.value.lastIndexOf(" ");
        const previousInput = this.state.value.substring(0, lastIndex);
        return `${previousInput} ${suggestion.name.toLowerCase()}`;
    };

    onFormSubmit(event) {
        event.preventDefault();
        this.props.fetchQuery(this.state.value);
    }

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
                                     getSuggestionValue={this.getSuggestionValue}
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