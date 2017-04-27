"use strict";
const junctions = require("../junctions/junctions");

class JunctionController {

    constructor(repository) {
        this.repository = repository;
    }

    getAll(req, res) {

    }

    addJunction(req, res) {
        const type = req.body.type;
        junctions.addJunction(type);
        let junction = junctions.getJunctionFromType(type);
        this.repository.save(junction, function () {
            res.sendStatus(200);
        });
    }

    removeJunction(req, res) {
        const type = req.params.type;
        junctions.remove(type);
        this.repository.destroy(type, function () {
            res.sendStatus(200);
        });
    }

    addKeyword(req, res) {
        junctions.addKeyword(req.params.type, req.body.keyword);
        let junction = junctions.getJunctionFromType(req.params.type);
        this.repository.update(junction, function () {
            res.sendStatus(200);
        });
    };

    removeKeyword(req, res) {
        junctions.removeKeyword(req.params.type, req.params.keyword);
        let junction = junctions.getJunctionFromType(req.params.type);
        this.repository.update(junction, function () {
            res.sendStatus(200);
        });
    };
}

module.exports = JunctionController;