import * as React from "react";
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';

export default class DocumentationBlock extends React.Component<any, any>  {
    renderOrderedList() {
        if (this.props.markup.orderedList) {
            return (
                <ol>
                    {this.props.markup.orderedList.map((lst, i) => {
                        return <li key={i}>{lst}</li>
                    })}
                </ol>
            );
        }
    }

    renderExamples() {
        if (this.props.markup.examples) {
            return (
                <div className="docs-example">
                    {this.props.markup.examples.map((example, i) => {
                        return <span key={i}>{example}</span>
                    })}
                </div>
            );
        }
    }

    renderText() {
        return this.props.markup.text ? this.props.markup.text : '';
    }

    render() {
        return (
            <div id={this.props.markup.id} name={this.props.markup.id}>
                {this.props.markup.title}
                {this.renderText()}
                {this.renderOrderedList()}
                {this.renderExamples()}
                <br/>
            </div>
        );
    }
}