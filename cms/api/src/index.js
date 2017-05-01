const express = require('express');
const bodyParser = require('body-parser');
const morgan = require('morgan');
const cors = require('cors');
const mongoose = require('mongoose');
const errors = require('./common/errors');
const mongodb = require("mongodb");
const Server = mongodb.Server;
const Db = mongodb.Db;

const TablesConverter = require("./tables/converter");
const MongoRepository = require('./persistence/repositories/mongoRepository');
const TableController = require("./controllers/tableController");
const JunctionController = require("./controllers/junctionController");
const OperatorController = require("./controllers/operatorController");
const OperatorsConverter = require("./operators/converter");
const Junctions = require("./junctions/junctions");
const JunctionsConverter = require("./junctions/converter");
const junctionRouter = require('./routers/junctionRouter');
const operatorRouter = require('./routers/operatorRouter');
const tableRouter = require('./routers/tableRouter');
const Connection = require("./persistence/connection");

const app = express();

const corsOptions = {
    origin: '*',
    methods: [
        'GET',
        'PUT',
        'POST',
        'PATCH',
        'DELETE',
        'UPDATE'
    ],
    credentials: true
};


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(express.static(__dirname + '/public'));

app.use(errors.genericErrorHandler);
app.use(morgan('combined'));
app.use(cors(corsOptions));

const server = new Server('localhost', 27017, {auto_reconnect: true});
const db = new Db('mql', server, {safe: true});
const connection = new Connection(db);
connection.open();


const junctionController = new JunctionController(new MongoRepository(connection.db, "junctions"), new JunctionsConverter);
const operatorController = new OperatorController(new MongoRepository(connection.db, "operators"), new OperatorsConverter);
const tableController = new TableController(new MongoRepository(connection.db, "entities"), new TablesConverter);

junctionRouter(app, junctionController);
operatorRouter(app, operatorController);
tableRouter(app, tableController);


const port = process.env.PORT || 3000;
app.listen(port);

console.log(`App started on port ${port}`);