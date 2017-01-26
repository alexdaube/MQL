import * as React from "react";
import SearchBar from "../containers/SearchBar";
import DynamicList from "../containers/DynamicList";

export interface HelloWorldProps {
    firstname: string;
    lastname: string;
}

export class App extends React.Component<HelloWorldProps, any> {
    render() {
        return (
            <div>
                <SearchBar />
                <DynamicList />
            </div>
        );
    }
}