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

exports.exportSchema = function (entities) {
    db.collection('entities').remove({});
    db.collection('entities').insert(entities, function (error) {
        if (error) throw error;
        console.log("Data inserted to db");
    });
};
