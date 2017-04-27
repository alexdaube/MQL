const td = require('testdouble');

const expressApp = {
    post: td.function("post"),
    get: td.function("get"),
    delete: td.function("delete"),
    put: td.function("put")
};

module.exports = td.object(expressApp);