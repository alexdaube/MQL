import {isInputEmpty, lowerCaseInput, undoCamelCasing} from "../../src/utils/strings";


describe('strings', () => {
    const camelCase = 'camelCase';
    const CamelCase = 'CamelCase';
    const formattedCamelCase = 'Camel Case';
    const upperCase = 'HELLO';
    const lowerCase = 'hello';
    const lowerCaseWithSpaces = ' hello ';
    const justSpaces = '     ';

    describe('undoCamelCasing', () => {
        it('should return a formatted string from camel case', () => {
            expect(undoCamelCasing(CamelCase)).to.be.eql(formattedCamelCase);
        });

        it('Should upper case the first label of each words', () => {
            expect(undoCamelCasing(camelCase)).to.be.eql(formattedCamelCase);
        });
    });

    describe('lowerCase', () => {
        it('should return lowered cased string', () => {
            expect(lowerCaseInput(upperCase)).to.be.eql(lowerCase);
        });

        it('should get rid of extra spaces', () => {
            expect(lowerCaseInput(lowerCaseWithSpaces)).to.be.eql(lowerCase);
        });
    });

    describe('isEmpty', () => {
        it('should be empty if only spaces present', () => {
            expect(isInputEmpty(justSpaces)).to.be.true;
        });

        it('should be empty if empty string', () => {
            expect(isInputEmpty(justSpaces)).to.be.true;
        });

        it('should not be empty if there are other characters than spaces', () => {
            expect(isInputEmpty(lowerCaseWithSpaces)).to.not.be.true;
        });
    });
});


