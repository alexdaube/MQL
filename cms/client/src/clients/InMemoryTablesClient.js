import Promise from 'promise';

export default class InMemoryTablesClient {

    constructor() {
        this.tables = [];
    }

    fetchTables() {
        return new Promise((resolve) => {resolve(this.tables)});
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
            resolve(this.tables);
        });
    }

    removeAttribute(tableName, attributeName) {
        return new Promise((resolve) => {
             this.tables = this.tables.map(t => {
                 if (t.name === tableName) {
                     return {...t, attributes: t.attributes.filter(a => a.name !== attributeName)}
                 }
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
            });
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
            });
            resolve(this.tables);
        });
    }
}