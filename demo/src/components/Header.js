import React, {Component} from "react";
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink} from "reactstrap";

export default class Header extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isOpen: false
        };

        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return (
            <div id="mql-navbar" className="mqlHeader">
                <Navbar color="primary" inverse toggleable>
                    <NavbarToggler right onClick={this.toggle}/>
                    <NavbarBrand href="/">MQL Demo</NavbarBrand>
                    <Collapse isOpen={this.state.isOpen} navbar>
                        <Nav className="ml-auto" navbar>
                            <NavItem>
                                <NavLink href="/">List</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="/documentation">Documentation</NavLink>
                            </NavItem>
                        </Nav>
                    </Collapse>
                </Navbar>
            </div>
        );
    }
}