import React, {Component} from "react";
import _ from "lodash";
import Autosuggest from 'react-autosuggest';
import {Button, Form, FormGroup, InputGroupButton, InputGroup, Badge} from "reactstrap";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import * as actions from "../actions";
import {getQueryKeywordBadgeDetails} from "../utils/badge";


const theme = {
    container: 'searchBarContainer',
    input: 'searchBarInput form-control',
    inputOpen:                'searchBarInputOpen',
    inputFocused:             'searchBarInputFocused',
    suggestionsContainer:     'searchBarSuggestionsContainer',
    suggestionsContainerOpen: 'searchBarSuggestionsContainerOpen',
    suggestionsList:          'searchBarSuggestionsList',
    suggestion:               'searchBarSuggestion',
    suggestionHighlighted:    'searchBarSuggestionHighlighted',
    sectionContainer:         'searchBarSectionContainer',
    sectionContainerFirst:    'searchBarSectionContainerFirst',
    sectionTitle:             'searchBarSectionTitle'
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
        this.onSuggestionsFetchRequested = this.onSuggestionsFetchRequested.bind(this);
        this.debouncedLoadSuggestions = _.debounce(this.props.fetchSuggestions, 300);
    }

    onSuggestionsFetchRequested(value) {
        this.debouncedLoadSuggestions(value);
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
        const { suggestions, clearSuggestions } = this.props;

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
                                     onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
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