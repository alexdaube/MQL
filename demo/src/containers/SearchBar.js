import React, {Component} from "react";
import _ from "lodash";
import moment from "moment";
import Autosuggest from "react-autosuggest";
import {Badge, Button, Form, FormGroup, InputGroup, InputGroupButton} from "reactstrap";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import * as actions from "../actions";
import {getQueryKeywordBadgeDetails} from "../utils/badge";


const theme = {
    container: 'searchBarContainer',
    input: 'searchBarInput form-control',
    inputOpen: 'searchBarInputOpen',
    inputFocused: 'searchBarInputFocused',
    suggestionsContainer: 'searchBarSuggestionsContainer',
    suggestionsContainerOpen: 'searchBarSuggestionsContainerOpen',
    suggestionsList: 'searchBarSuggestionsList',
    suggestion: 'searchBarSuggestion',
    suggestionHighlighted: 'searchBarSuggestionHighlighted',
    sectionContainer: 'searchBarSectionContainer',
    sectionContainerFirst: 'searchBarSectionContainerFirst',
    sectionTitle: 'searchBarSectionTitle'
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
    let synonyms = '';
    let suggestionType = getQueryKeywordBadgeDetails(suggestion.type);
    if (suggestion.hasOwnProperty("synonyms")) {
        synonyms = suggestion.synonyms.join(', ');
    }
    return (
        <div>
            <Badge color={suggestionType.color}>&nbsp;{suggestionType.label}&nbsp;</Badge>&nbsp;
            {`${suggestion.name} ${synonyms}`}
        </div>
    );
};

export class SearchBar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value: ''
        };
        this.onFormSubmit = this.onFormSubmit.bind(this);
        this.getSuggestionValue = this.getSuggestionValue.bind(this);
        this.onSuggestionsFetchRequested = this.onSuggestionsFetchRequested.bind(this);
        this.debouncedLoadSuggestions = _.debounce(this.props.fetchSuggestions, props.suggestionRate);
    }

    onSuggestionsFetchRequested(value) {
        this.debouncedLoadSuggestions(value);
    }

    onChange = (event, {newValue}) => {
        this.setState({
            value: newValue
        });
    };

    getSuggestionValue(suggestion) {
        const lastIndex = this.state.value.lastIndexOf(" ");
        const previousInput = this.state.value.substring(0, lastIndex);
        if (suggestion.type === 'DATE') {
            return `${previousInput} ${moment().format('YYYY-MM-DD')}`;
        }

        if (suggestion.type === 'INTEGER') {
            return `${previousInput} 0`;
        }

        if (suggestion.type === 'DECIMAL') {
            return `${previousInput} 0.0`;
        }

        if (suggestion.type === 'VARCHAR') {
            return `${previousInput} "value"`;
        }

        return `${previousInput} ${suggestion.name.toLowerCase()}`;
    };

    onFormSubmit(event) {
        event.preventDefault();
        this.props.fetchQuery(this.state.value);
    }

    render() {
        const {value} = this.state;
        const {suggestions, clearSuggestions} = this.props;

        const inputProps = {
            placeholder: 'Basic query format is Entity + Attribute + Operator + Value',
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