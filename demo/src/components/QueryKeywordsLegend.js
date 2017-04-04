import React, {Component} from 'react';
import {Badge} from "reactstrap";
import {KEYWORDS, getQueryKeywordBadgeDetails} from "../utils/badge"
import guid from "../utils/guid"

export default class QueryKeywordsLegend extends Component {
    showQueryBadgesLegend() {
        let result = [];
        Object.keys(KEYWORDS).forEach((key) => {
            const details = getQueryKeywordBadgeDetails(KEYWORDS[key]);
            result.push(
                <li key={guid()} className="list-inline-item">
                    <h6>
                        <Badge color={details.color}>&nbsp;{details.label}&nbsp;</Badge>
                        &nbsp;{KEYWORDS[key]}&nbsp;
                    </h6>
                </li>
            );
        });
        return result;
    }

    render() {
        return (
            <div className="text-center">
                <ul className="list-inline">
                    {this.showQueryBadgesLegend()}
                </ul>
            </div>
        );
    }
}
