import React from 'react';
import {connect} from 'react-redux';

import Keyword from '../components/Keyword';
import KeywordEntry from '../components/KeywordEntry';
import Menu from '../components/Menu';
import {fetchTables, createTable, addAttribute, addTableSynonym, addAttributeSynonym, removeTable, removeAttribute,
    removeTableSynonym, removeAttributeSynonym
} from '../../actions/tablesActions';

import './TablesManager.css';

@connect((store) => {
    return {
        tables: store.tables.tables
    };
})
export default class TablesManager extends React.Component {

    componentWillMount() {
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

    render() {
        const tables = this.props.tables.map((t, key) => {
            const attributes = t.attributes.map((a, key) => {
                const synonyms = a.synonyms.map((s, key) =>
                    <Keyword key={key} name={s} removeKeyword={()=>this.removeAttributeSynonym(t.name, a.name, s)} />);
                return <Keyword key={key} name={a.name} removeKeyword={()=>this.removeAttribute(t.name, a.name)}>
                        <h3>Synonyms:</h3>
                        <KeywordEntry addKeyword={(name) => this.addAttributeSynonym(t.name, a.name, name)}/>
                        {synonyms}
                </Keyword>
            });
            const synonyms = t.synonyms.map((s, key) => <Keyword key={key} name={s} removeKeyword={()=>this.removeTableSynonym(t.name, s)} />);
            return <Keyword key={key} name={t.name} removeKeyword={()=>this.removeTable(t.name)}>
                <Menu top={true}>
                    <Menu>
                        <h3>Synonyms:</h3>
                        <KeywordEntry addKeyword={(name) => this.addSynonym(t.name, name)}/>
                        {synonyms}
                    </Menu>
                    <Menu>
                        <h3>Attributes:</h3>
                        <KeywordEntry addKeyword={(name) => this.addAttribute(t.name, name)}/>
                        {attributes}
                    </Menu>
                </Menu>
            </Keyword>;
        });
        return (
            <div className="tables">
                <h1>Tables</h1>
                <Menu>
                    <KeywordEntry addKeyword={this.addTable.bind(this)} />
                    {tables}
                </Menu>
            </div>
        );
    }
}