import * as React from "react";
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {fetchQuery} from "../actions/queryActions";


interface ISearchBarState {
    term: string
}

class SearchBar extends React.Component<any, ISearchBarState> {
    constructor(props) {
        super(props);
        this.state = {
            term: ''
        };

        this.onInputChange = this.onInputChange.bind(this);
        this.onFormSubmit = this.onFormSubmit.bind(this);
    }

    onInputChange(event) {
        this.setState({
            term: event.target.value
        });
    }

    onFormSubmit(event) {
        event.preventDefault();
        this.props.fetchQuery(this.state.term);
        this.setState({ term: '' });
    }

    render() {
        return (
        <Form inline onSubmit={this.onFormSubmit}>
            <FormGroup>
                <Input
                    type="text"
                    value={this.state.term}
                    onChange={this.onInputChange}
                    id="exampleText"
                    placeholder="Basic query format is Keyword + Operator + Value" />
            </FormGroup>
            <Button>Submit</Button>
        </Form>
        );
    }

}

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({ fetchQuery }, dispatch);
};


export default connect(null, mapDispatchToProps)(SearchBar)