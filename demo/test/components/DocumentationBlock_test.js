import React from "react";
import DocumentationBlock from "../../src/components/DocumentationBlock";

describe('DocumentationBlock', () => {
    let wrapper, props;

    beforeEach(() => {
        props = {markup: {id: 'someId', title: 'someTitle'}};
        wrapper = mount(<DocumentationBlock  {...props} />);
    });

    it('has the correct class', () => {
        expect(wrapper).to.have.className('documentationSection');
    });

    it('has an ordered list block', () => {
        props.markup.orderedList = ['orderedList'];
        wrapper = mount(<DocumentationBlock  {...props} />);
        expect(wrapper.find('.mqlDocumentationOrderedList')).to.exist;
    });

    it('does not have an ordered list block', () => {
        expect(wrapper.find('.mqlDocumentationOrderedList')).not.to.exist;
    });

    it('has example block', () => {
        props.markup.examples = ['example'];
        wrapper = mount(<DocumentationBlock  {...props} />);
        expect(wrapper.find('.docs-example')).to.exist;
    });

    it('does not have example block', () => {
        expect(wrapper.find('.docs-example')).not.to.exist;
    });
});
