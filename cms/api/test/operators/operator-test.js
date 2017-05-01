const td = require('testdouble');
const Operator = require("../../src/operators/operator");
const expect = require("chai").expect;

describe('Operator', () => {
    const type = "operator";
    const keyword = "keyword";
    const newKeyword = "newkeyword";
    const keywords = [keyword];
    let operator;

    beforeEach(() => {
        operator = new Operator(type, keywords);
    });

    afterEach(() => {
        td.reset();
    });

    describe("addKeyword", () => {
        it('should not add any keyword if it already exists', () => {
            operator.addKeyword(keyword);
            expect(operator.getKeywords()).to.have.lengthOf(1);
        });

        it("should add a keyword if it doesn't exists already", () => {
            operator.addKeyword(newKeyword);
            expect(operator.getKeywords()).to.have.lengthOf(2);
        });
    });

    describe("removeKeyword", () => {
        it("should not remove a keyword if it doesn't exists", () => {
            operator.removeKeyword(newKeyword);
            expect(operator.getKeywords()).to.have.lengthOf(1);
        });

        it('should remove the keyword', () => {
            operator.removeKeyword(keyword);
            expect(operator.getKeywords()).to.have.lengthOf(0);
        });
    });
});


