import React, {Component} from 'react';
import Helmet from 'react-helmet';
import SearchBar from "../containers/SearchBar";
import DynamicList from "../containers/DynamicList";

export default class ListView extends Component {
    render() {
        return (
            <div>
                <Helmet title="List Search -- MQL" />
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