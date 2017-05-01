const td = require('testdouble');
const Junctions = require("../../src/junctions/junctions");
const Junction = require("../../src/junctions/junction");
const expect = require("chai").expect;

describe('Junction', () => {
    const type = "junction";
    const newType = "newJunction";
    const keyword = "keyword";
    const newKeyword = "newkeyword";
    const keywords = [keyword];
    let junctions;

    beforeEach(() => {
        junctions = new Junctions([new Junction(type, keywords)]);
    });

    afterEach(() => {
        td.reset();
    });

    describe("addJunction", () => {
        it('should not add an existing junction', () => {
            junctions.addJunction(type);
            expect(junctions.getJunctions()).to.have.lengthOf(1);
        });

        it("should add a new junction", () => {
            junctions.addJunction(newType);
            expect(junctions.getJunctions()).to.have.lengthOf(2);
        });
    });

    describe("remove", () => {
        it('should not remove a non existing junction', () => {
            junctions.remove(newType);
            expect(junctions.getJunctions()).to.have.lengthOf(1);
        });

        it("should remove an existing junction", () => {
            junctions.remove(type);
            expect(junctions.getJunctions()).to.have.lengthOf(0);
        });
    });

    describe("addKeyword", () => {
        it('should not add any keyword if it already exists', () => {
            junctions.addKeyword(type, keyword);
            expect(junctions.getJunctions()[0].getKeywords()).to.have.lengthOf(1);
        });

        it("should add a keyword if it doesn't exists already", () => {
            junctions.addKeyword(type, newKeyword);
            expect(junctions.getJunctions()[0].getKeywords()).to.have.lengthOf(2);
        });
    });

    describe("removeKeyword", () => {
        it("should not remove a keyword if it doesn't exists", () => {
            junctions.removeKeyword(type, newKeyword);
            expect(junctions.getJunctions()[0].getKeywords()).to.have.lengthOf(1);
        });

        it('should remove the keyword', () => {
            junctions.removeKeyword(type, keyword);
            expect(junctions.getJunctions()[0].getKeywords()).to.have.lengthOf(0);
        });
    });
});


