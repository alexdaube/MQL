const td = require('testdouble');
const router = require('../../src/router');


function ExpressApp() {}
ExpressApp.prototype.post = function () {};


describe('Router', () => {
    let app;

    beforeEach(() => {
        app = td.object(ExpressApp);
        router(app);
    });

    afterEach(() => {
        td.reset();
    });

    it('should make a post request', () => {
        td.verify(app.post("/"));
    });
});