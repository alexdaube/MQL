const Junctions = require("../junctions/junctions");
const Junction = require("../junctions/junction");

"use strict";
class JunctionController {

    constructor(connection) {
        this.connection = connection;
    }

    addJunction(type, res) {
        this.connection.getExistingJunctions((dbJunctions) => {
            let junctions = mapToJunction(dbJunctions);
            console.log(junctions.getJunctions());
            junctions.addJunction(type);
            let junction = junctions.getJunctionFromType(type);
            this.connection.saveJunction(junction, function () {
                res.sendStatus(200);
            });
        });
    }

    removeJunction(type, res) {
        this.connection.getExistingJunctions((dbJunctions) => {
            let junctions = mapToJunction(dbJunctions);
            junctions.remove(type);
            this.connection.deleteJunction(type, function () {
                res.sendStatus(200);
            });
        });
    }

    addKeyword(req, res) {
        this.connection.getExistingJunctions((dbJunctions) => {
            let junctions = mapToJunction(dbJunctions);
            junctions.addKeyword(req.params.type, req.body.keyword);
            let junction = junctions.getJunctionFromType(req.params.type);
            this.connection.updateJunction(junction, function () {
                res.sendStatus(200);
            });
        });
    };

    removeKeyword(req, res) {
        this.connection.getExistingJunctions((dbJunctions) => {
            let junctions = mapToJunction(dbJunctions);
            let junction = junctions.getJunctionFromType(req.params.type);
            junctions.removeKeyword(req.params.type, req.params.keyword);
            this.connection.updateJunction(junction, function () {
                res.sendStatus(200);
            });
        });
    };
}

function mapToJunction(dbJunctions) {
    return new Junctions(dbJunctions.map(j => new Junction(j.type, j.keywords)));
}
module.exports = JunctionController;