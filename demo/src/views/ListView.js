import React, {Component} from "react";
import Helmet from "react-helmet";
import {Alert, Badge} from "reactstrap";
import {connect} from "react-redux";
import SearchBar from "../containers/SearchBar";
import DynamicList from "../containers/DynamicList";
import {KEYWORDS, getQueryKeywordBadgeDetails} from "../utils/badge"
import guid from "../utils/guid"

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

    showQueryBadgesLegend() {
        let result = [];
        Object.keys(KEYWORDS).forEach((key) => {
            const details = getQueryKeywordBadgeDetails(KEYWORDS[key]);
            result.push(
                <li key={guid()} className="list-inline-item">
                    <h6>
                        <Badge color={details.color}>&nbsp;{details.letter}&nbsp;</Badge>
                        &nbsp;{KEYWORDS[key]}&nbsp;
                    </h6>
                </li>
            );
        });
        return result;
    }

    render() {
        return (
            <div className="mqlListView">
                <Helmet title="List Search -- MQL"/>
                <div className="text-center">
                    <ul className="list-inline">
                        {this.showQueryBadgesLegend()}
                    </ul>
                </div>
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