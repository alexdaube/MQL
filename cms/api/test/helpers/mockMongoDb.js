const td = require('testdouble');

const db = {
    open: td.function("open"),
    collection: td.function("collection"),
    find: td.function("find"),
    insertOne: td.function("insertOne"),
    findOneAndDelete: td.function("findOneAndDelete"),
    update: td.function("update"),
};

module.exports = {
    db: td.object(db),
};