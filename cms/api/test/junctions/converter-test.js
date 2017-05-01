const td = require('testdouble');
const JunctionsConverter = require("../../src/junctions/converter");
const expect = require("chai").expect;

describe('JunctionsConverter', () => {
    const type = "junctions";
    const fetchedJunctions = [{type, keywords: []}];
    const emptyFetchedJunctions = [];
    let converter;

    beforeEach(() => {
        converter = new JunctionsConverter;
    });

    afterEach(() => {
        td.reset();
    });

    describe("mapToJunction", () => {
        it('converts the returned dbJunctions to Junctions object', () => {
            const junctions = converter.mapToJunction(fetchedJunctions);
            expect(junctions.getJunctions()).to.have.lengthOf(1);
        });

        it('returns an empty object by default', () => {
            const junctions = converter.mapToJunction(emptyFetchedJunctions);
            expect(junctions.getJunctions()).to.have.lengthOf(0);
        });
    });
});


