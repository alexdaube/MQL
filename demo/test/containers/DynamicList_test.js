import React from "react";
import {CSVLink} from "react-csv";
import {DynamicList} from "../../src/containers/DynamicList";
import DynamicListHeader from "../../src/components/DynamicListHeader";
import DynamicListBody from "../../src/components/DynamicListBody";


describe('DynamicList', () => {
    const data = [[{name: 'SomeColumn', value: "test1"}, {name: 'SomeOther', value: "test2"}]];
    const formattedColumns = ['Some Column', 'Some Other'];
    const flattenedData = [{SomeColumn: "test1", SomeOther: "test2"}];
    let wrapper, props;

    beforeEach(() => {
        props = {query: {data: data}};
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

    it('should have a CSVLink', () => {
        const wrapper = shallow(<DynamicList {...props}/>);
        expect(wrapper.find(CSVLink)).to.have.length(1);
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

    it('should flatten the props query data', () => {
        expect(wrapper.instance().getFlattenData()).to.be.eql(flattenedData);
    });
});


