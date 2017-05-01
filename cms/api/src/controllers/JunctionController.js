const Junctions = require("../junctions/junctions");

"use strict";

class JunctionController {

    constructor(repository, converter) {
        this.repository = repository;
        this.converter = converter;
    }

    getAll(req, res) {
        const junctions = this.getCurrentJunctions();
        if (junctions instanceof Junctions) {
            return res.send(junctions);
        }
        res.sendStatus(404);
    }

    addJunction(req, res) {
        const junctions = this.getCurrentJunctions();
        if (junctions instanceof Junctions) {
            const type = req.body.type;
            junctions.addJunction(type);
            const junction = junctions.getJunctionFromType(type);
            const doc = this.repository.save(junction);
            if (!doc.hasError) {
                return res.sendStatus(200);
            }
        }
        res.sendStatus(404);
    }

    removeJunction(req, res) {
        const junctions = this.getCurrentJunctions();
        if (junctions instanceof Junctions) {
            const type = req.params.type;
            junctions.remove(type);
            const destroy = this.repository.destroy({type: type});
            if (!destroy.hasError) {
                res.sendStatus(200);
            }
        }
        res.sendStatus(404);
    }

    addKeyword(req, res) {
        const junctions = this.getCurrentJunctions();
        if (junctions instanceof Junctions) {
            const type = req.params.type;
            const keyword = req.body.keyword;
            junctions.addKeyword(type, keyword);
            return this.updateJunction(junctions, type, res);
        }
        res.sendStatus(404);
    }

    removeKeyword(req, res) {
        const junctions = this.getCurrentJunctions();
        if (junctions instanceof Junctions) {
            const type = req.params.type;
            const keyword = req.params.keyword;
            junctions.removeKeyword(type, keyword);
            return this.updateJunction(junctions, type, res);
        }
        res.sendStatus(404);
    }

    updateJunction(junctions, type, res) {
        const junction = junctions.getJunctionFromType(type);
        const typeObj = {type: junction.getType()};
        const keywords = {keywords: junction.getKeywords()};
        const update = this.repository.update(typeObj, keywords);
        if (!update.hasError) {
            return res.sendStatus(200);
        }
        return res.sendStatus(404);
    }

    getCurrentJunctions() {
        const fetchedJunctions = this.repository.getAll();
        return this.converter.mapToJunction(fetchedJunctions);
    }
}

module.exports = JunctionController;