import React, {Component} from "react";
import Helmet from "react-helmet";
import {Alert} from "reactstrap";
import {connect} from "react-redux";
import SearchBar from "../containers/SearchBar";
import DynamicList from "../containers/DynamicList";

export class ListView extends Component {
    showAlert() {
        if (this.props.query.error) {
            return (
                <Alert color="danger">
                    {this.props.query.error}
                </Alert>
            );
        }
    }

    render() {
        return (
            <div className="mqlListView">
                <Helmet title="List Search -- MQL"/>
                {this.showAlert()}
                <div>
                    <SearchBar />
                </div>
                <div>
                    <DynamicList />
                </div>
            </div>
        );
    }
}

const mapStateToProps = ({query}) => {
    return {query};
};

export default connect(mapStateToProps)(ListView);