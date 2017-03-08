// import {renderComponent, expect} from '../test_helper';
// import DocumentationBlock from '../../src/components/DocumentationBlock';
//
// describe('DocumentationBlock', () => {
//     let component, props;
//
//     beforeEach(() => {
//         props = {markup: {id: 'someId', title: 'someTitle' }};
//         component = renderComponent(DocumentationBlock, props);
//     });
//
//     it('has the correct class', () => {
//         expect(component).to.have.class('documentationSection');
//     });
//
//     it('has an ordered list block', () => {
//         props.markup.orderedList = ['orderedList'];
//         component = renderComponent(DocumentationBlock, props);
//         expect(component.find('.mqlDocumentationOrderedList')).to.exist;
//     });
//
//     it('does not have an ordered list block', () => {
//         expect(component.find('.mqlDocumentationOrderedList')).not.to.exist;
//     });
//
//     it('has example block', () => {
//         props.markup.examples = ['example'];
//         component = renderComponent(DocumentationBlock, props);
//         expect(component.find('.docs-example')).to.exist;
//     });
//
//     it('does not have example block', () => {
//         expect(component.find('.docs-example')).not.to.exist;
//     });
// });
