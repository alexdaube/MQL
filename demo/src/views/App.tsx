import * as React from "react";
import Header from "../components/Header";

export class App extends React.Component<any, any> {
    render() {
        return (
            <div>
                <Header/>
                <br/>
                {this.props.children}
            </div>
        );
    }
}