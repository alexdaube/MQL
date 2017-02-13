import * as React from "react";
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
        this.setState({ term: '' });
    }

    render() {
        return (
            <form onSubmit={this.onFormSubmit} className="input-group">
                <input
                    placeholder="Basic query format is Keyword + Operator + Value"
                    className="form-control"
                    value={this.state.term}
                    onChange={this.onInputChange} />
                <span className="input-group-btn">
                    <button type="submit" className="btn btn-secondary">
                        Submit
                    </button>
                </span>
            </form>
        );
    }

}

const mapDispatchToProps = (dispatch) => {
    return bindActionCreators({ fetchQuery: actions.fetchQuery }, dispatch);
};


export default connect(null, mapDispatchToProps)(SearchBar)