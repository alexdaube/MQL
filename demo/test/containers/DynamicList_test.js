import React from "react";
import {DynamicList} from "../../src/containers/DynamicList";
import DynamicListHeader from "../../src/components/DynamicListHeader";
import DynamicListBody from "../../src/components/DynamicListBody";


describe('DynamicList', () => {
    const columns = [[{name: 'SomeColumn'},{name: 'SomeOther'}]];
    const formattedColumns = ['Some Column', 'Some Other'];
    let wrapper, props;

    beforeEach(() => {
        props = {query: {data: columns}};
        wrapper = mount(<DynamicList {...props} />);
    });

    it('should have correct class', () => {
        expect(wrapper.find('.mqlDynamicList')).to.be.present();
    });

    it('should have a DynamicListHeader', () => {
        const wrapper = shallow(<DynamicList {...props}/>);
        expect(wrapper.find(DynamicListHeader)).to.have.length(1);
    });

    it('should have a DynamicListBody', () => {
        const wrapper = shallow(<DynamicList {...props}/>);
        expect(wrapper.find(DynamicListBody)).to.have.length(1);
    });

    it('should not show a DynamicListBody and DynamicListHeader when there is no query', () => {
        props.query.data = [];
        const wrapper = shallow(<DynamicList {...props}/>);
        expect(wrapper.find(DynamicListHeader)).to.have.length(0);
        expect(wrapper.find(DynamicListBody)).to.have.length(0);
    });

    it('should format the column headers properly', () => {
        expect(wrapper.instance().extractLabels()).to.be.eql(formattedColumns);
    });
});


