const Tables = require("../tables/tables");

class TableController {

    constructor(repository, converter) {
        this.repository = repository;
        this.converter = converter;
    }

    getAll(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            return res.send(tables);
        }
        res.sendStatus(404);
    }

    addTable(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const name = req.body.name;
            tables.addTable(name);
            const table = tables.getTableFromName(name);
            const doc = this.repository.save(table);
            if (!doc.hasError) {
                return res.sendStatus(200);
            }
        }
        res.sendStatus(404);
    }

    removeTable(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const name = req.params.name;
            tables.removeTable(name);
            const destroy = this.repository.destroy({name: name});
            if (!destroy.hasError) {
                res.sendStatus(200);
            }
        }
        res.sendStatus(404);
    }

    addColumn(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const tableName = req.params.name;
            const columnName = req.body.name;
            tables.addColumn(tableName, columnName);
            return this.updateTable(tables, tableName, res);
        }
        res.sendStatus(404);
    }

    removeColumn(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const tableName = req.params.name;
            const columnName = req.params.column;
            tables.removeColumn(tableName, columnName);
            return this.updateTable(tables, tableName, res);
        }
        res.sendStatus(404);
    }

    addKeyword(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const tableName = req.params.name;
            const keywordName = req.body.keyword;
            tables.addKeyword(tableName, keywordName);
            return this.updateTable(tables, tableName, res);
        }
        res.sendStatus(404);
    }
    removeKeyword(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const tableName = req.params.name;
            const keywordName = req.params.keyword;
            tables.removeKeyword(tableName, keywordName);
            return this.updateTable(tables, tableName, res);
        }
        res.sendStatus(404);
    }
    addForeignKey(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const tableName = req.params.name;
            const foreignKey = req.body;
            tables.addForeignKey(tableName, foreignKey);
            return this.updateTable(tables, tableName, res);
        }
        res.sendStatus(404);
    }
    removeForeignKey(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const tableName = req.params.name;
            const foreignKey = req.body;
            tables.removeForeignKey(tableName, foreignKey);
            return this.updateTable(tables, tableName, res);
        }
        res.sendStatus(404);
    }
    addColumnKeyword(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const tableName = req.params.name;
            const columnName = req.params.column;
            const keywordName = req.body.keyword;
            tables.addColumnKeyword(tableName, columnName, keywordName);
            return this.updateTable(tables, tableName, res);
        }
        res.sendStatus(404);
    }
    removeColumnKeyword(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            const tableName = req.params.name;
            const columnName = req.params.column;
            const keywordName = req.params.keyword;
            tables.removeColumnKeyword(tableName, columnName, keywordName);
            return this.updateTable(tables, tableName, res);
        }
        res.sendStatus(404);
    }

    updateTable(tables, tableName, res) {
        const table = tables.getTableFromName(tableName);
        const name = {name: table.getName()};
        const keywords = {
            keywords: table.getKeywords(),
            columns: table.getColumns(),
            foreignKeys: table.getForeignKeys()
        };
        const update = this.repository.update(name, keywords);

        if (!update.hasError) {
            return res.sendStatus(200);
        }
        return res.sendStatus(404);
    }

    getCurrentTables() {
        const fetchedTables = this.repository.getAll();
        return this.converter.mapToTables(fetchedTables);
    }
}

module.exports = TableController;