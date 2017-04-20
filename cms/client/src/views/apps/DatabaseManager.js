import React from "react";
import {connect} from "react-redux";

import './DatabaseManager.css'
import Keyword from '../components/Keyword';
import KeywordEntry from '../components/KeywordEntry';
import Menu from '../components/Menu';
import ForeignKeyEntry from '../components/ForeignKeyEntry';
import {bindActionCreators} from "redux";
import * as actions from '../../actions/tablesActions';

@connect((store) => {
    return {
        tables: store.tables.tables
    };
}, (dispatch) => {
    return bindActionCreators({
        fetchTables: actions.fetchTables,
        addTable: actions.addTable,
        addAttribute: actions.addTableAttribute,
        addForeignKey: actions.addTableForeignKey,
        removeTable: actions.removeTable,
        removeAttribute: actions.removeTableAttribute,
        removeForeignKey: actions.removeTableForeignKey
    }, dispatch);
})
export default class DatabaseManager extends React.Component {

    componentWillMount() {
        this.props.fetchTables();
    }

    render() {
        const tables = this.props.tables.map((t, key) => {
            const attributes = t.attributes.map((a, key) =>
                <Keyword className="database-attribute" key={key} name={a.name} removeKeyword={this.props.removeAttribute.bind(this, t.name, a.name)} />);
            const foreignKeys = t.foreignKeys.map((f, key) =>
                <Keyword className="database-foreignKey" key={key} name={f.toAttribute} removeKeyword={this.props.removeForeignKey.bind(this, t.name, f.fromAttribute, f.toTable, f.toAttribute)} />);
            return <Keyword className="database-table" key={key} name={t.name} removeKeyword={this.props.removeTable.bind(this, t.name)}>
                <Menu top={true}>
                    <div className="database-container">
                        <Menu>
                            <h3>Attributes:</h3>
                            <KeywordEntry className="attribute-entry" addKeyword={this.props.addAttribute.bind(this, t.name)}/>
                            {attributes}
                        </Menu>
                    </div>
                    <div className="database-container">
                        <Menu>
                            <h3>Foreign keys:</h3>
                            <ForeignKeyEntry addKeyword={this.props.addForeignKey.bind(this, t.name)}
                                             fromTable={t} tables={this.props.tables}/>
                            {foreignKeys}
                        </Menu>
                    </div>
                </Menu>
            </Keyword>
        });
        return (
            <div className="database-manager">
                <h1>Tables</h1>
                <KeywordEntry className="table-entry" addKeyword={this.props.addTable.bind(this)} />
                {tables}
            </div>
        );
    }
}