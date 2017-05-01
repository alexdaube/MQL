const Tables = require("../tables/tables");

class TableController {

    constructor(repository, converter) {
        this.repository = repository;
        this.converter = converter;
        this.getAll = this.getAll.bind(this);
        this.addTable = this.addTable.bind(this);
        this.removeTable = this.removeTable.bind(this);
        this.addColumn = this.addColumn.bind(this);
        this.removeColumn = this.removeColumn.bind(this);
        this.addKeyword = this.addKeyword.bind(this);
        this.removeKeyword = this.removeKeyword.bind(this);
        this.addForeignKey = this.removeKeyword.bind(this);
        this.removeForeignKey = this.removeForeignKey.bind(this);
        this.addColumnKeyword = this.removeForeignKey.bind(this);
        this.removeColumnKeyword = this.removeForeignKey.bind(this);
        this.updateTable = this.updateTable.bind(this);
        this.getCurrentTables = this.getCurrentTables.bind(this);
    }

    getAll(req, res) {
        const tables = this.getCurrentTables();
        if (tables instanceof Tables) {
            return res.send(tables);
        }
        res.sendStatus(404);
    }

    addTable(req, res) {
        this.getCurrentTables((tables) => {
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
        });
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

    getCurrentTables(callback) {
        this.repository.getAll((fetchedTables) => {
            callback(this.converter.mapToTables(fetchedTables));
        });
    }
}

module.exports = TableController;