import {getQueryKeywordBadgeDetails, KEYWORDS} from "../../src/utils/badge";


describe('badge utils', () => {
    const detailsCaseTest = (keywordType, expectedColor, expectedLabel) => {
        it(`should have ${expectedColor} color and '${expectedLabel}' label badge for ${keywordType} keyword`, () => {
            const details = getQueryKeywordBadgeDetails(keywordType);
            expect(details.color).to.be.eql(expectedColor);
            expect(details.label).to.be.eql(expectedLabel);
        });
    };

    detailsCaseTest(KEYWORDS.operator, 'default', 'O');
    detailsCaseTest('BETWEEN', 'default', 'O');
    detailsCaseTest('GREATER', 'default', 'O');
    detailsCaseTest('LESS', 'default', 'O');
    detailsCaseTest('EQUAL', 'default', 'O');
    detailsCaseTest('NOT', 'default', 'O');
    detailsCaseTest('LIKE', 'default', 'O');
    detailsCaseTest('OTHER', 'default', 'O');
    detailsCaseTest(KEYWORDS.attribute, 'danger', 'A');
    detailsCaseTest(KEYWORDS.entity, 'primary', 'E');
    detailsCaseTest(KEYWORDS.value, 'success', 'V');
    detailsCaseTest("DATE", 'success', 'V');
    detailsCaseTest("DECIMAL", 'success', 'V');
    detailsCaseTest("VARCHAR", 'success', 'V');
    detailsCaseTest("INTEGER", 'success', 'V');
    detailsCaseTest(KEYWORDS.junction, 'warning', 'J');
    detailsCaseTest("AND", 'warning', 'J');
    detailsCaseTest("OR", 'warning', 'J');
    detailsCaseTest("DEFAULT_CASE", 'default', 'N/A');
});



