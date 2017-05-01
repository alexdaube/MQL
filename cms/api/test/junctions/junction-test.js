const td = require('testdouble');
const Junction = require("../../src/junctions/junction");
const expect = require("chai").expect;

describe('Junction', () => {
    const type = "junction";
    const keyword = "keyword";
    const newKeyword = "newkeyword";
    const keywords = [keyword];
    let junction;

    beforeEach(() => {
        junction = new Junction(type, keywords);
    });

    afterEach(() => {
        td.reset();
    });

    describe("addKeyword", () => {
        it('should not add any keyword if it already exists', () => {
            junction.addKeyword(keyword);
            expect(junction.getKeywords()).to.have.lengthOf(1);
        });

        it("should add a keyword if it doesn't exists already", () => {
            junction.addKeyword(newKeyword);
            expect(junction.getKeywords()).to.have.lengthOf(2);
        });
    });

    describe("removeKeyword", () => {
        it("should not remove a keyword if it doesn't exists", () => {
            junction.removeKeyword(newKeyword);
            expect(junction.getKeywords()).to.have.lengthOf(1);
        });

        it('should remove the keyword', () => {
            junction.removeKeyword(keyword);
            expect(junction.getKeywords()).to.have.lengthOf(0);
        });
    });
});

