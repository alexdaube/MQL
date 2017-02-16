import * as React from "react";
import SearchBar from "../containers/SearchBar";
import DynamicList from "../containers/DynamicList";

export default class ListView extends React.Component<any, any> {
    render() {
        return (
            <div className="container-fluid">
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