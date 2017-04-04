import React from "react";
import {Badge} from "reactstrap";

import QueryKeywordsLegend from "../../src/components/QueryKeywordsLegend";

describe('QueryKeywordsLegend', () => {
    let wrapper;

    beforeEach(() => {
        wrapper = shallow(<QueryKeywordsLegend />);
    });

    it('creates a badge for each types', () => {
        expect(wrapper.find(Badge)).to.have.length(5);
    });
});

