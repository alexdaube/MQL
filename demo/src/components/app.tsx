import * as React from 'react';

import SearchBar from '../containers/search-bar';
import DynamicList from "../containers/dynamic-list";

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