var exports = module.exports = {};
var _ = require('underscore');
const mongodb = require("mongodb");
const Server = mongodb.Server;
const BSON = mongodb.BSONPure;
const Db = mongodb.Db;

const server = new Server('localhost', 27017, {auto_reconnect: true});
db = new Db('mql', server, {safe: true});

db.open((err, db) => {
    if (!err) {
        console.log("Connected to db");
    }
});

exports.saveTable = function (table, callback) {
    db.collection('entities').insertOne(table, function (err, doc) {
        if (err) throw err;
        callback(doc);
    });
};

exports.deleteTable = function (name, callback) {
    db.collection('entities').findOneAndDelete({name: name}, function (err, doc) {
        if (err) throw err;
        callback();
    });
};

exports.updateTable = function (table, callback) {
    db.collection('entities').update({name: table.getName()}, {
            $set: {
                keywords: table.getKeywords(),
                columns: table.getColumns(),
                foreignKeys: table.getForeignKeys()
            }
        },
        function (err, doc) {
            if (err) throw err;
            callback();
        });
};

exports.saveOperator = function (operator, callback) {
    db.collection('operators').insertOne(operator, function (err, doc) {
        if (err) throw err;
        callback(doc);
    });
};

exports.deleteOperator = function (name, callback) {
    db.collection('operators').findOneAndDelete({type: name}, function (err, doc) {
        if (err) throw err;
        callback();
    });
};

exports.updateOperator = function (operator, callback) {
    db.collection('operators').update({type: operator.getType()}, {
            $set: {
                keywords: operator.getKeywords()
            }
        },
        function (err, doc) {
            if (err) throw err;
            callback();
        });
};

exports.saveJunction = function (operator, callback) {
    db.collection('junctions').insertOne(operator, function (err, doc) {
        if (err) throw err;
        callback(doc);
    });
};

exports.deleteJunction = function (name, callback) {
    db.collection('junctions').findOneAndDelete({type: name}, function (err, doc) {
        if (err) throw err;
        callback();
    });
};

exports.updateJunction = function (junction, callback) {
    db.collection('junctions').update({type: junction.getType()}, {
            $set: {
                keywords: junction.getKeywords()
            }
        },
        function (err, doc) {
            if (err) throw err;
            callback();
        });
};

exports.getExistingTables = function (callback) {
    var tables = [];
    var cursor = db.collection('entities').find();
    cursor.each(function (err, doc) {
        if (err) throw err;
        if (doc != null) {
            tables.push(doc);
        } else {
            callback(tables);
        }
    });
};

exports.getExistingOperators = function (callback) {
    var operators = [];
    var cursor = db.collection('operators').find();
    cursor.each(function (err, doc) {
        if (err) throw err;
        if (doc != null) {
            operators.push(doc);
        } else {
            callback(operators);
        }
    });
};

exports.getExistingJunctions = function (callback) {
    var junctions = [];
    var cursor = db.collection('junctions').find();
    cursor.each(function (err, doc) {
        if (err) throw err;
        if (doc != null) {
            junctions.push(doc);
        } else {
            callback(junctions);
        }
    });
};
