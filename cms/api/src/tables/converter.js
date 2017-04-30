const Tables = require("./tables");
const Table = require("./table");
const ForeignKey = require("./ForeignKey");
const Column = require("./Column");


class TablesConverter {
    mapToTables(dbTables) {
        return new Tables(dbTables.map(t => new Table(t.name, t.keywords,
            t.columns.map(c => new Column(c.name, c.keywords)),
            t.foreignKeys.map(f => new ForeignKey(f.fromColumn, f.toTable, f.toColumn)))
        ));
    }
}

module.exports = TablesConverter;
