import * as React from "react";
import * as Helmet from 'react-helmet';
import * as pdfConverter from 'jspdf';
import * as $ from 'jquery';
import { Link } from 'react-router';
import {Container, Nav, NavItem, NavLink, Row, Col, Button} from "reactstrap";


const navItems = [
    {name: 'Basic Syntax', to: '#'},
    {name: 'Entities', to: '#'},
    {name: 'Attributes', to: '#'},
    {name: 'Operators', to: '#'},
    {name: 'Junctions', to: '#'},
    {name: 'Query Examples', to: '#'},
];

const ComponentLink = (props) => {
    return (
        <NavItem>
            <NavLink tag={Link} to={props.item.to} activeClassName="active" >
                {props.item.name}
            </NavLink>
        </NavItem>
    );
};

export default class DocumentationView extends React.Component<any, any> {
    constructor(props) {
        super(props);
        this.state = {
            navItems: navItems
        };
    }

    downloadPDF() {
        const doc = new pdfConverter();
        doc.fromHTML($("#documentation-content").get(0), 20, 20, { width: 180});
        doc.output("dataurlnewwindow")
        //doc.save('MQL_usage_documentation.pdf');
    }

    render() {
        return (
            <Container className="content">
                <Helmet title="Documentation -- MQL" />
                <Row>
                    <Col md={{ size: 3}}>
                        <div className="docs-sidebar mb-3">
                            <h5>MQL</h5>
                            <Nav className="flex-column">
                                {this.state.navItems.map((item, i) => {
                                    return <ComponentLink key={i} item={item} />;
                                })}
                                <NavItem>
                                    <Button color="danger" size="sm" onClick={this.downloadPDF}><small>Download PDF</small></Button>
                                </NavItem>
                            </Nav>
                        </div>
                    </Col>
                    <Col id="documentation-content" md={{ size: 9}}>
                        <h3>What is MQL?</h3>
                        <p>
                            MQL is a simple query language that enables you to search the database
                            from the search bar with a common tongue. It enables you to make really make fined grain searches
                            that really gives you what you need to look at.
                        </p>
                        <br/>

                        <h3>Basic Syntax</h3>
                        <p>
                            MQL is quite simple. It is making queries to the database with specific
                            keywords that makes it looks like you are searching making a common english phrase.
                            Every successful queries follow this order of keywords.
                        </p>
                        <div className="docs-example">
                            ENTITY WHERE ATTRIBUTE IS VALUE
                        </div>
                        <br/>

                        <h3>Entities</h3>
                        <p>They are basically the main data type you are looking to search into. Search queries start with it.</p>
                        <div className="docs-example">
                            <p><kbd>equipments</kbd> where id is 3</p>
                            <p><kbd>sites</kbd> where province = Quebec</p>
                        </div>
                        <br/>

                        <h3>Attributes</h3>
                        <p>They are fields owned by the <i>Entities</i> inside the our database. The attributes need to be prepended by the word <i>where</i>.
                            Following the declaration of an attribute, there need to be an <i>operator</i> and a <i>value</i> for the attribute to be useful.
                        </p>
                        <div className="docs-example">
                            <p>equipments where <kbd>id</kbd> is 3</p>
                            <p>sites where <kbd>province</kbd> = Quebec</p>
                        </div>
                        <br/>

                        <h3>Operators</h3>
                        <div className="docs-example">
                            <p>Some text about Operators</p>
                        </div>
                        <br/>

                        <h3>Junctions</h3>
                        <div className="docs-example">
                            <p>Some text about Junctions</p>
                        </div>
                        <br/>

                        <h3>Query Examples</h3>
                        <div className="docs-example">
                            <p>Some query examples</p>
                        </div>
                        <br/>

                    </Col>
                </Row>
            </Container>
        );
    }
}