import * as React from "react";
import {Button, Form, FormGroup, Label, Input, InputGroupButton, InputGroup} from "reactstrap";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import * as actions from "../actions";


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
        this.setState({term: ''});
    }

    render() {
        return (
            <Form onSubmit={this.onFormSubmit}>
                <FormGroup>
                    <InputGroup>
                        <Input
                            type="text"
                            value={this.state.term}
                            onChange={this.onInputChange}
                            id="exampleText"
                            placeholder="Basic query format is Keyword + Operator + Value"/>
                        <InputGroupButton>
                            <Button color="success">Submit</Button>
                        </InputGroupButton>
                    </InputGroup>
                </FormGroup>
            </Form>
        );
    }

}

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({fetchQuery: actions.fetchQuery}, dispatch);
};


export default connect(null, mapDispatchToProps)(SearchBar)