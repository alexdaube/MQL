import {flattenData} from "../../src/utils/data";


describe('data', () => {
    describe('flattenData', () => {
        const initialData = [[{name: "Id", value: 1},{name: "Name",value: "Bingo"}],[{name: "Id", value: 2},{name: "Name",value: "Test"}]];
        const flattenedData = [{Id: 1, Name: "Bingo"},{Id: 2, Name: "Test"}];
        it('should return an array of objects where each objects represent a row', () => {
            expect(flattenData(initialData)).to.be.eql(flattenedData);
        });
    });
});


