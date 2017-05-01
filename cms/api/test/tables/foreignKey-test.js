const td = require('testdouble');
const ForeignKey = require("../../src/tables/foreignKey");
const expect = require("chai").expect;

describe('ForeignKey', () => {
    const fromColumn = "fromColumn";
    const fromColumn2 =  "newFromColumn";
    const toTable = "toTable";
    const toTable2 = "toTable2";
    const toColumn = "toColumn";
    const toColumn2 = "toColumn2";
    let foreignKey;

    beforeEach(() => {
        foreignKey = new ForeignKey(fromColumn, toTable, toColumn);
    });

    afterEach(() => {
        td.reset();
    });

    describe("isEqualTo", () => {
        it('should not be equal if one attribute differs', () => {
            const anotherForeignKey = new ForeignKey(fromColumn2, toTable2, toColumn2);
            expect(foreignKey.isEqualTo(anotherForeignKey)).to.be.false;
        });

        it("should be equal if all attributes are the same", () => {
            const anotherForeignKey = new ForeignKey(fromColumn, toTable, toColumn);
            expect(foreignKey.isEqualTo(anotherForeignKey)).to.be.true;
        });
    });
});


