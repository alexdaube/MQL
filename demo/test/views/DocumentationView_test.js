import React from "react";
import DocumentationView from "../../src/views/DocumentationView";


describe('DocumentationView', () => {
    const hashPath = '#path';
    const markupId = 'someItemId';
    let wrapper, props;

    beforeEach(() => {
        spy(DocumentationView.prototype, 'componentDidMount');
        spy(DocumentationView.prototype, 'componentWillUnmount');
        props = {location: {hash: hashPath}, route: {markup: [{id: markupId}]}};
        wrapper = mount(<DocumentationView {...props}/>);
    });

    afterEach(() => {
        DocumentationView.prototype.componentDidMount.restore();
        DocumentationView.prototype.componentWillUnmount.restore();
    });

    it('has the correct class', () => {
        expect(wrapper).to.have.className('mqlDocumentationView');
    });

    it('shows a sidebar menu', () => {
        expect(wrapper.find('.mqlDocumentationSidebar')).to.be.present();
    });

    it('shows the content', () => {
        expect(wrapper.find('.mqlDocumentationContent')).to.be.present();
    });

    it('calls componentDidMount', () => {
        expect(DocumentationView.prototype.componentDidMount.calledOnce).to.equal(true);
    });

    it('calls componentWillUnmount', () => {
        wrapper.unmount();
        expect(DocumentationView.prototype.componentWillUnmount.calledOnce).to.equal(true);
    });

    it('sets navActiveItem with location hash when available', () => {
        const wrapper = shallow(<DocumentationView {...props}/>);
        expect(wrapper.state().navActiveItem).to.equal(hashPath.substring(1));
    });

    it('sets navActiveItem defaults to first nav item', () => {
        props.location.hash = '';
        const wrapper = shallow(<DocumentationView {...props}/>);
        expect(wrapper.state().navActiveItem).to.equal(markupId);
    });

    it('changes the active item on click', () => {
        wrapper.instance().handleNavClick(markupId);
        expect(wrapper.state().navActiveItem).to.equal(markupId);
    });
});