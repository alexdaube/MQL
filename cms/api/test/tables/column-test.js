const td = require('testdouble');
const Column = require("../../src/tables/column");
const expect = require("chai").expect;

describe('Column', () => {
    const name = "column";
    const keyword = "keyword";
    const newKeyword = "newkeyword";
    const keywords = [keyword];

    beforeEach(() => {
        column = new Column(name, keywords);
    });

    afterEach(() => {
        td.reset();
    });

    describe("addKeyword", () => {
        it('should not add any keyword if it already exists', () => {
            column.addKeyword(keyword);
            expect(column.getKeywords()).to.have.lengthOf(1);
        });

        it("should add a keyword if it doesn't exists already", () => {
            column.addKeyword(newKeyword);
            expect(column.getKeywords()).to.have.lengthOf(2);
        });
    });

    describe("removeKeyword", () => {
        it("should not remove a keyword if it doesn't exists", () => {
            column.removeKeyword(newKeyword);
            expect(column.getKeywords()).to.have.lengthOf(1);
        });

        it('should remove the keyword', () => {
            column.removeKeyword(keyword);
            expect(column.getKeywords()).to.have.lengthOf(0);
        });
    });
});

