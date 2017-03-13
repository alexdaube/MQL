import {undoCamelCasing} from "../../src/utils/strings";


describe('strings', () => {
    const camelCase = 'camelCase';
    const CamelCase = 'CamelCase';
    const formattedCamelCase = 'Camel Case';

    it('should return a formatted string from camel case', () => {
        expect(undoCamelCasing(CamelCase)).to.be.equal(formattedCamelCase);
    });

    it('Should upper case the first letter of each words', () => {
        expect(undoCamelCasing(camelCase)).to.be.equal(formattedCamelCase);
    });
});


