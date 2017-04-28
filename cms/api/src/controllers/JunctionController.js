"use strict";

class JunctionController {

    constructor(repository, junctions) {
        this.repository = repository;
        this.junctions = junctions;
    }

    getAll(req, res) {}

    addJunction(req, res) {
        const type = req.body.type;
        this.junctions.addJunction(type);
        let junction = this.junctions.getJunctionFromType(type);
        this.repository.save(junction, function () {
            res.sendStatus(200);
        });
    }

    removeJunction(req, res) {
        const type = req.params.type;
        this.junctions.remove(type);
        this.repository.destroy(type, function () {
            res.sendStatus(200);
        });
    }

    addKeyword(req, res) {
        this.junctions.addKeyword(req.params.type, req.body.keyword);
        let junction = this.junctions.getJunctionFromType(req.params.type);
        this.repository.update(junction, function () {
            res.sendStatus(200);
        });
    };

    removeKeyword(req, res) {
        this.junctions.removeKeyword(req.params.type, req.params.keyword);
        let junction = this.junctions.getJunctionFromType(req.params.type);
        this.repository.update(junction, function () {
            res.sendStatus(200);
        });
    };
}

module.exports = JunctionController;