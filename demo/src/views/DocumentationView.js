import React, {Component} from "react";
import PropTypes from 'prop-types';
import Helmet from "react-helmet";
import jsPDF from "jspdf";
import $ from "jquery";
import {Sticky, StickyContainer} from "react-sticky";
import {Button, Col, Container, Nav, NavItem, Row} from "reactstrap";
import DocumentationBlock from "../components/DocumentationBlock";
import ComponentLink from "../components/ComponentLink";


export default class DocumentationView extends Component {
    static contextTypes = {
        router: PropTypes.object
    };

    constructor(props) {
        super(props);
        const locationHash = this.props.location.hash;
        this.state = {
            navActiveItem: locationHash ? locationHash.substring(1) : this.props.route.markup[0].id
        };
    }

    handleScroll() {
        const sections = $('.documentationSection');
        const nav = $('#mql-navbar');
        const nav_height = nav.outerHeight();

        const cur_pos = $(window).scrollTop();

        sections.each((index, element) => {
            const top = $(element).offset().top - nav_height;
            const bottom = top + $(element).outerHeight();
            if (cur_pos >= top && cur_pos <= bottom) {
                this.setState({navActiveItem: $(element).attr('id')});
            }
        });
    }

    componentDidMount() {
        window.addEventListener('scroll', this.handleScroll.bind(this), false);
    }

    componentWillUnmount() {
        window.removeEventListener('scroll', this.handleScroll.bind(this), false);
    }

    handleNavClick(location) {
        this.setState({navActiveItem: location})
    }

    downloadPDF() {
        const printDoc = new jsPDF();
        printDoc.fromHTML($($("#documentation-content")).get(0), 20, 20, {'width': 180});
        printDoc.output("dataurlnewwindow");
    }

    render() {
        return (
            <StickyContainer className="mqlDocumentationView">
                <Container className="content">
                    <Helmet title="Documentation -- MQL"/>
                    <Row>
                        <Col md={{size: 3}} className="mqlDocumentationSidebar">
                            <div className="docs-sidebar mb-3">
                                <h5>MQL</h5>
                                <Sticky>
                                    <Nav className="flex-column">
                                        {this.props.route.markup.map((item, i) => {
                                            return (<ComponentLink key={i}
                                                                   item={item}
                                                                   toBaseUrl='/documentation#'
                                                                   activeItem={this.state.navActiveItem}
                                                                   onActiveChange={() => {
                                                                       this.handleNavClick(item.id)
                                                                   }}/>);
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
                        <Col id="documentation-content" md={{size: 9}} className="mqlDocumentationContent">
                            {this.props.route.markup.map((docBlock, i) => {
                                return <DocumentationBlock key={i} markup={docBlock}/>
                            })}
                        </Col>
                    </Row>
                </Container>
            </StickyContainer>
        );
    }
}