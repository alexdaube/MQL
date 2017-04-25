import React from 'react';
import Select from 'react-select';
import 'react-select/dist/react-select.css';

import './ForeignKeyEntry.css';

export default class ForeignKeyEntry extends React.Component {
    constructor() {
        super(...arguments);
        this.state = {
            fromAttribute: "",
            toTable: "",
            toAttribute: "",
        }
    }

    changeFromAttribute(value) {
        const v = value === null ? "":value.value;
        this.setState({...this.state, fromAttribute: v});
    }

    changeToTable(value) {
        const v = value === null ? "":value.value;
        this.setState({...this.state, toTable: v});
    }

    changeToAttribute(value) {
        const v = value === null ? "":value.value;
        this.setState({...this.state, toAttribute: v});
    }

    submit(event) {
        if (this.state.fromAttribute.length !== 0 && this.state.toTable.length !== 0 && this.state.toAttribute.length !== 0) {
            this.props.addKeyword(this.state.fromAttribute, this.state.toTable, this.state.toAttribute);
            this.setState({...this.state, fromAttribute: "", toTable: "", toAttribute: ""});
        }
        event.preventDefault();
    }

    render() {
        const fromAttributeOptions = this.props.fromTable.attributes.map(a => {return {value: a.name, label :a.name}});
        const tableOptions = this.props.tables.filter(t => t.name !== this.props.fromTable.name)
            .map(t => {return {value: t.name, label: t.name}});
        const currentToTable = this.props.tables.find((t) => t.name === this.state.toTable);
        const toAttributeOptions = currentToTable ? currentToTable.attributes.map(a => {return {value: a.name, label: a.name}}):[];
        return (
            <form className="form-inline foreign-key-form" onSubmit={this.submit.bind(this)}>
                <Select className="foreign-key-select foreign-key-from-attribute" value={this.state.fromAttribute} autosize={false}
                        placeholder="From attribute" options={fromAttributeOptions} onChange={this.changeFromAttribute.bind(this)} />
                <Select className="foreign-key-select foreign-key-to-table" value={this.state.toTable} autosize={false}
                        placeholder="To table" options={tableOptions} onChange={this.changeToTable.bind(this)} />
                <Select className="foreign-key-select foreign-key-to-attribute" value={this.state.toAttribute} autosize={false}
                        placeholder="To attribute" options={toAttributeOptions} onChange={this.changeToAttribute.bind(this)} />
                <input className="btn btn-primary" type="submit" value="Add"/>
            </form>
        );
    }
}