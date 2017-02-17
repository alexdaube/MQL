import * as React from "react";
import * as Helmet from "react-helmet";
import * as jsPDF from "jspdf";
import * as $ from "jquery";
import {Link} from "react-router";
import {StickyContainer, Sticky} from "react-sticky";
import {Container, Nav, NavItem, NavLink, Row, Col, Button} from "reactstrap";
import DocumentationBlock from "../components/DocumentationBlock";
import {documentBlocksMarkup} from "../constants/demo_text";


const ComponentLink = (props) => {
    return (
        <NavItem>
            <NavLink tag={Link} to={`/documentation#${props.item.id}`} activeClassName="active">
                {props.item.menuTitle}
            </NavLink>
        </NavItem>
    );
};


export default class DocumentationView extends React.Component<any, any> {
    constructor(props) {
        super(props);
    }

    downloadPDF() {
        const printDoc = new jsPDF();
        printDoc.fromHTML($($("#documentation-content")).get(0), 20, 20, {'width': 180});
        printDoc.output("dataurlnewwindow");
    }

    render() {
        return (
            <StickyContainer>
                <Container className="content">
                    <Helmet title="Documentation -- MQL"/>
                    <Row>
                        <Col md={{ size: 3}}>
                            <div className="docs-sidebar mb-3">
                                <h5>MQL</h5>
                                <Sticky>
                                    <Nav className="flex-column">
                                        {documentBlocksMarkup.map((item, i) => {
                                            return <ComponentLink key={i} item={item}/>;
                                        })}
                                        <NavItem>
                                            <Button color="danger" size="sm" onClick={this.downloadPDF}>
                                                <small>Download PDF</small>
                                            </Button>
                                        </NavItem>
                                    </Nav>
                                </Sticky>
                            </div>
                        </Col>
                        <Col id="documentation-content" md={{ size: 9}}>
                            {documentBlocksMarkup.map((docBlock, i) => {
                                return <DocumentationBlock key={i} markup={docBlock}/>
                            })}
                        </Col>
                    </Row>
                </Container>
            </StickyContainer>
        );
    }
}