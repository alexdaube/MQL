export function toDto(table) {
    return {
        name: table.name,
        keywords: table.synonyms,
        columns: table.attributes.map(toAttributeDto),
        foreignKeys: table.foreignKeys
    }
}

export function fromDto(table) {
    return {
        name: table.keyword,
        synonyms: table.synonyms,
        attributes: table.attributes.map(fromAttributeDto),
        foreignKeys: table.foreign_keys.map(fromForeignKeyDto)
    }
}

export function toAttributeDto(attribute) {
    return {
        name: attribute.name,
        keywords: attribute.synonyms
    }
}

export function fromAttributeDto(column) {
    return {
        name: column.name,
        synonyms: column.keywords
    }
}

export function toForeignKeyDto(foreignKey) {
    return {
        fromColumn: foreignKey.fromAttribute,
        toTable: foreignKey.toTable,
        toColumn: foreignKey.toAttribute
    }
}

export function fromForeignKeyDto(foreignKey) {
    return {
        fromAttribute: foreignKey.fromColumn,
        toTable: foreignKey.toTable,
        toAttribute: foreignKey.toColumn
    }
}