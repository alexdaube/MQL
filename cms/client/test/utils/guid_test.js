import guid from '../../src/utils/guid';


describe('guid', () => {
    it('should always be unique', () => {
        expect(guid()).to.not.be.equal(guid());
    });

    it('should have 36 characters', () => {
        expect(guid()).to.have.length(36);
    });
});


