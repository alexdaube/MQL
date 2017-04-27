const express = require('express');
const bodyParser = require('body-parser');
const morgan = require('morgan');
const cors = require('cors');
const mongoose = require('mongoose');
const errors = require('./common/errors');
const mongodb = require("mongodb");
const Server = mongodb.Server;
const Db = mongodb.Db;
const JunctionRepository = require('./persitence/repositories/junctionRepository');
const OperatorRepository = require('./persitence/repositories/operatorRepository');
const TableRepository = require('./persitence/repositories/tableRepository');
const TableController = require("./controllers/TableController");
const JunctionController = require("./controllers/JunctionController");
const OperatorController = require("./controllers/OperatorController");
const junctionRouter = require('./routers/junctionRouter');
const operatorRouter = require('./routers/operatorRouter');
const tableRouter = require('./routers/tableRouter');
const Connection = require("./persitence/connection");

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

const junctionRepository = new JunctionRepository(connection);
const operatorRepository = new OperatorRepository(connection);
const tableRepository = new TableRepository(connection);

const junctionController = new JunctionController(junctionRepository);
const operatorController = new OperatorController(operatorRepository);
const tableController = new TableController(tableRepository);

junctionRouter(app, junctionController);
operatorRouter(app, operatorController);
tableRouter(app, tableController);


const port = process.env.PORT || 3000;
app.listen(port);

console.log(`App started on port ${port}`);