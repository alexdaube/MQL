import Promise from "promise";

export default class InMemoryTablesClient {

    constructor() {
        this.tables = [];
    }

    fetchTables() {
        return new Promise((resolve) => {
            resolve(this.tables)
        });
    }

    addTable(table) {
        return new Promise((resolve) => {
            if (this.tables.filter(t => t.name === table.name).length === 0) {
                this.tables = this.tables.concat(table);
            }
            resolve(this.tables);
        })
    }

    addAttribute(tableName, attribute) {
        return new Promise((resolve) => {
            this.tables = this.tables.map((t) => {
                if (t.name === tableName) {
                    if (t.attributes.filter(a => a.name === attribute.name).length === 0) {
                        return {...t, attributes: t.attributes.concat(attribute)};
                    }
                }
                return t;
            });
            resolve(this.tables);
        });
    }

    addSynonym(tableName, synonym) {
        return new Promise((resolve) => {
            this.tables = this.tables.map(t => {
                if (t.name === tableName) {
                    if (t.synonyms.filter(s => s === synonym).length === 0) {
                        return {...t, synonyms: t.synonyms.concat(synonym)};
                    }
                }
                return t;
            });
            resolve(this.tables);
        });
    }

    addForeignKey(fromTableName, fromAttributeName, toTableName, toAttributeName) {
        return new Promise((resolve) => {
            const fromTable = this.tables.find((t) => t.name === fromTableName);
            const toTable = this.tables.find((t) => t.name === toTableName);
            if (fromTable && toTable) {
                const fromAttribute = fromTable.attributes.find((a) => a.name === fromAttributeName);
                const toAttribute = toTable.attributes.find((a) => a.name === toAttributeName);
                if (fromAttribute && toAttribute) {
                    this.tables = this.tables.map((t) => {
                        if (t.name === fromTableName) {
                            if (!t.foreignKeys.find((f) => f.fromAttribute === fromAttributeName && f.toTable === toTableName && f.toAttribute === toAttributeName)) {
                                return {
                                    ...t,
                                    foreignKeys: t.foreignKeys.concat({
                                        fromAttribute: fromAttributeName,
                                        toTable: toTableName,
                                        toAttribute: toAttributeName
                                    })
                                }
                            }
                        }
                        return t;
                    })
                }
            }
            resolve(this.tables);
        });
    }

    addAttributeSynonym(tableName, attributeName, synonym) {
        return new Promise((resolve) => {
            this.tables = this.tables.map(t => {
                if (t.name === tableName) {
                    const attributes = t.attributes.map(a => {
                        if (a.name === attributeName) {
                            if (a.synonyms.filter(s => s === synonym).length === 0) {
                                return {...a, synonyms: a.synonyms.concat(synonym)};
                            }
                        }
                        return a;
                    });
                    return {...t, attributes: attributes};
                }
                return t;
            });
            resolve(this.tables);
        });
    }

    removeTable(name) {
        return new Promise((resolve) => {
            this.tables = this.tables.filter(t => t.name !== name);
            this.tables = this.tables.map((t) => {
                return {...t, foreignKeys: t.foreignKeys.filter(f => f.toTable !== name)};
            });
            resolve(this.tables);
        });
    }

    removeAttribute(tableName, attributeName) {
        return new Promise((resolve) => {
            this.tables = this.tables.map(t => {
                if (t.name === tableName) {
                    return {...t, attributes: t.attributes.filter(a => a.name !== attributeName)}
                }
                return t;
            });
            this.tables = this.tables.map(t => {
               return {...t, foreignKeys: t.foreignKeys.filter(f => f.toAttribute !== attributeName && f.fromAttribute !== attributeName)};
            });
            resolve(this.tables);
        });
    }

    removeSynonym(tableName, synonym) {
        return new Promise((resolve) => {
            this.tables = this.tables.map(t => {
                if (t.name === tableName) {
                    return {...t, synonyms: t.synonyms.filter(s => s !== synonym)}
                }
                return t;
            });
            resolve(this.tables);
        });
    }

    removeForeignKey(fromTableName, fromAttributeName, toTableName, toAttributeName) {
        return new Promise((resolve) => {
            const fromTable = this.tables.find((t) => t.name === fromTableName);
            const toTable = this.tables.find((t) => t.name === toTableName);
            if (fromTable && toTable) {
                const fromAttribute = fromTable.attributes.find((a) => a.name === fromAttributeName);
                const toAttribute = toTable.attributes.find((a) => a.name === toAttributeName);
                if (fromAttribute && toAttribute) {
                    this.tables = this.tables.map((t) => {
                        if (t.name === fromTableName) {
                            return {
                                ...t,
                                foreignKeys: t.foreignKeys.filter((f) => f.fromAttribute !== fromAttributeName || f.toTable !== toTableName || f.toAttribute !== toAttributeName)
                            }
                        }
                        return t;
                    })
                }
            }
            resolve(this.tables);
        });
    }

    removeAttributeSynonym(tableName, attributeName, synonym) {
        return new Promise((resolve) => {
            this.tables = this.tables.map(t => {
                if (t.name === tableName) {
                    const attributes = t.attributes.map(a => {
                        if (a.name === attributeName) {
                            return {...a, synonyms: a.synonyms.filter(s => s !== synonym)};
                        }
                        return a;
                    });
                    return {...t, attributes: attributes}
                }
                return t;
            });
            resolve(this.tables);
        });
    }
}