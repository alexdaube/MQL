const mongodb = require("mongodb");
const keyword = require("./keywords/keywords");

const Server = mongodb.Server;
const Db = mongodb.Db;
const BSON = mongodb.BSONPure;


const server = new Server('localhost', 27017, {auto_reconnect: true});
db = new Db('mql', server, {safe: true});

db.open((err, db) => {
    if (!err) {
        console.log("Connected to db");
    }
});

module.exports = function (app) {
    app.get('/entities', (req, res) => {
        db.collection('entities').find().toArray((error, items) => {
            res.send(items);
        });
    });

    app.post('/entities/add', (req, res) => {
        keyword.addEntity(req.body.name, req.body.synonyms);
        res.send("Showtime");
    });

    app.post('/entities', (req, res) => {
        const entities = req.body.entities;
        db.collection('entities').remove({});
        db.collection('entities').insert(entities, function (error, record) {
            if (error) throw error;
            console.log("data saved");
            res.status(200).send(record);
        });
    });
};