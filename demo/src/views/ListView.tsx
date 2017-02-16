import * as React from "react";
import * as Helmet from 'react-helmet';
import SearchBar from "../containers/SearchBar";
import DynamicList from "../containers/DynamicList";

export default class ListView extends React.Component<any, any> {
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