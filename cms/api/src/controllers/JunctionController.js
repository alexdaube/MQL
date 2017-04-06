const junctions = require("../junctions/junctions");

"use strict";
class JunctionController {

    constructor(connection) {
        this.connection = connection;
    }

    addJunction(type, res) {
        junctions.addJunction(type);
        let junction = junctions.getJunctionFromType(type);
        this.connection.saveJunction(junction, function () {
            res.sendStatus(200);
        });
    }

    removeJunction(type, res) {
        junctions.remove(type);
        this.connection.deleteJunction(type, function () {
            res.sendStatus(200);
        });
    }

    addKeyword(req, res) {
        junctions.addKeyword(req.params.type, req.body.keyword);
        let junction = junctions.getJunctionFromType(req.params.type);
        this.connection.updateJunction(junction, function () {
            res.sendStatus(200);
        });
    };

    removeKeyword(req, res) {
        junctions.removeKeyword(req.params.type, req.params.keyword);
        let junction = junctions.getJunctionFromType(req.params.type);
        this.connection.updateJunction(junction, function () {
            res.sendStatus(200);
        });
    };
}

module.exports = JunctionController;