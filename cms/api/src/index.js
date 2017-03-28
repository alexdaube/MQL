const express = require('express');
const bodyParser = require('body-parser');
const morgan = require('morgan');
const cors = require('cors');
const mongoose = require('mongoose');
const errors = require('./common/errors');
const router = require('./router');

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

app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(express.static(__dirname + '/public'));

app.use(errors.genericErrorHandler);
app.use(morgan('combined'));
app.use(cors(corsOptions));

router(app);

const port = process.env.PORT || 3000;
app.listen(port);

console.log(`App started on port ${port}`);