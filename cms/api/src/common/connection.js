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

exports.saveTable = function (table, callback) {
    db.collection('entities').insertOne(table, function (err, doc) {
        if (err) throw err;
        callback(doc);
    });
};

exports.deleteTable = function (table_name, callback) {
    db.collection('entities').findOneAndDelete({name: table_name}, function (err, doc) {
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

exports.exportTables = function (entities) {
    db.collection('entities').drop();
    db.createCollection('entities', function (err, collection) {
        collection.insertMany(entities, function (err, result) {
            if (err) throw err;
        })
    });
};

exports.exportJunctions = function (junctions) {
    db.collection('junctions').drop();
    db.createCollection('junctions', function (err, collection) {
        collection.insertMany(junctions, function (err, result) {
            if (err) throw err;
        })
    });
};


exports.exportOperators = function (operators) {
    db.collection('operators').drop();
    db.createCollection('operators', function (err, collection) {
        collection.insertMany(operators, function (err, result) {
            if (err) throw err;
        })
    });
};

exports.printTables = function () {
    var collection = db.collection('entities');
    var stream = collection.find().stream();
    stream.on('data', function (doc) {
        console.log(doc);
    });
};
