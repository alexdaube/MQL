import * as React from "react";
import SearchBar from "../containers/SearchBar";
import DynamicList from "../containers/DynamicList";

export class App extends React.Component<any, any> {
    render() {
        return (
            <div>
                <SearchBar />
                <DynamicList />
            </div>
        );
    }
}