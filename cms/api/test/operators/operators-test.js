const td = require('testdouble');
const Operators = require("../../src/operators/operators");
const Operator = require("../../src/operators/operator");
const expect = require("chai").expect;

describe('Operators', () => {
    const type = "operator";
    const newType = "newOperator";
    const keyword = "keyword";
    const newKeyword = "newkeyword";
    const keywords = [keyword];
    let operators;

    beforeEach(() => {
        operators = new Operators([new Operator(type, keywords)]);
    });

    afterEach(() => {
        td.reset();
    });

    describe("addOperator", () => {
        it('should not add an existing operator', () => {
            operators.addOperator(type);
            expect(operators.getOperators()).to.have.lengthOf(1);
        });

        it("should add a new operator", () => {
            operators.addOperator(newType);
            expect(operators.getOperators()).to.have.lengthOf(2);
        });
    });

    describe("remove", () => {
        it('should not remove a non existing operator', () => {
            operators.remove(newType);
            expect(operators.getOperators()).to.have.lengthOf(1);
        });

        it("should remove an existing operator", () => {
            operators.remove(type);
            expect(operators.getOperators()).to.have.lengthOf(0);
        });
    });

    describe("addKeyword", () => {
        it('should not add any keyword if it already exists', () => {
            operators.addKeyword(type, keyword);
            expect(operators.getOperators()[0].getKeywords()).to.have.lengthOf(1);
        });

        it("should add a keyword if it doesn't exists already", () => {
            operators.addKeyword(type, newKeyword);
            expect(operators.getOperators()[0].getKeywords()).to.have.lengthOf(2);
        });
    });

    describe("removeKeyword", () => {
        it("should not remove a keyword if it doesn't exists", () => {
            operators.removeKeyword(type, newKeyword);
            expect(operators.getOperators()[0].getKeywords()).to.have.lengthOf(1);
        });

        it('should remove the keyword', () => {
            operators.removeKeyword(type, keyword);
            expect(operators.getOperators()[0].getKeywords()).to.have.lengthOf(0);
        });
    });
});


