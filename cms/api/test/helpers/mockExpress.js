const td = require('testdouble');

const expressApp = {
    post: td.function("post"),
    get: td.function("get"),
    delete: td.function("delete"),
    put: td.function("put")
};

const mockResponse = {
    sendStatus: td.function("sendStatus"),
    send: td.function("send")
};

module.exports = {
    app: td.object(expressApp),
    res: mockResponse
};
