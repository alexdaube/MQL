import React from "react";
import {
    fetchTables,
    createTable,
    addAttribute,
    addTableSynonym,
    addAttributeSynonym,
    removeTable,
    removeAttribute,
    removeTableSynonym,
    removeAttributeSynonym,
    removeTableForeignKey,
    addTableForeignKey
} from "../../actions/tablesActions";

export default class TableModifier extends React.Component {

    fetchTables() {
        this.props.dispatch(fetchTables());
    }

    addTable(name) {
        this.props.dispatch(createTable(name));
    }

    addAttribute(tableName, attributeName) {
        this.props.dispatch(addAttribute(tableName, attributeName))
    }

    addSynonym(tableName, synonym) {
        this.props.dispatch(addTableSynonym(tableName, synonym));
    }

    addAttributeSynonym(tableName, attributeName, synonym) {
        this.props.dispatch(addAttributeSynonym(tableName, attributeName, synonym));
    }

    addForeignKey(fromTableName, fromAttributeName, toTableName, toAttributeName) {
        this.props.dispatch(addTableForeignKey(fromTableName, fromAttributeName, toTableName, toAttributeName));
    }

    removeTable(name) {
        this.props.dispatch(removeTable(name));
    }

    removeAttribute(tableName, attributeName) {
        this.props.dispatch(removeAttribute(tableName, attributeName));
    }

    removeTableSynonym(tableName, synonym) {
        this.props.dispatch(removeTableSynonym(tableName, synonym));
    }

    removeAttributeSynonym(tableName, attributeName, synonym) {
        this.props.dispatch(removeAttributeSynonym(tableName, attributeName, synonym));
    }

    removeForeignKey(fromTableName, fromAttributeName, toTableName, toAttributeName) {
        this.props.dispatch(removeTableForeignKey(fromTableName, fromAttributeName, toTableName, toAttributeName));
    }
}