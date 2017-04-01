const keyword = require("./keywords/keywords");
const connection = require("./common/connection");


module.exports = function (app) {
    app.get('/entities', (req, res) => {
        res.send(keyword.getEntities());
    });

    app.post('/entities/add', (req, res) => {
        keyword.addEntity(req.body.name, req.body.synonyms);
        res.send("Hello");
    });

    app.post('/entities/:name/add', (req, res) => {
        keyword.addAttribute(req.params.name, req.body.name, req.body.synonyms);
        res.send("Hello");
    });

    app.get('/entities/export', (req, res) => {
        connection.exportSchema(keyword.getEntities());
        res.sendStatus(200);
    });
    app.post('/entities', (req, res) => {

    });
};