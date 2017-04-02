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
