const td = require('testdouble');
const OperatorsConverter = require("../../src/operators/converter");
const expect = require("chai").expect;

describe('OperatorsConverter', () => {
    const type = "operators";
    const fetchedOperators = [{type, keywords: []}];
    const emptyFetchedOperators = [];
    let converter;

    beforeEach(() => {
        converter = new OperatorsConverter;
    });

    afterEach(() => {
        td.reset();
    });

    describe("mapToOperator", () => {
        it('converts the returned dbOperators to Operators object', () => {
            const operators = converter.mapToOperator(fetchedOperators);
            expect(operators.getOperators()).to.have.lengthOf(1);
        });

        it('returns an empty object by default', () => {
            const operators = converter.mapToOperator(emptyFetchedOperators);
            expect(operators.getOperators()).to.have.lengthOf(0);
        });
    });
});


