import React from "react";
import {connect} from "react-redux";

import './DatabaseManager.css'
import TableModifier from "./TableModifier";
import Keyword from '../components/Keyword';
import KeywordEntry from '../components/KeywordEntry';
import Menu from '../components/Menu';
import ForeignKeyEntry from '../components/ForeignKeyEntry';

@connect((store) => {
    return {
        tables: store.tables.tables
    };
})
export default class DatabaseManager extends TableModifier {

    componentWillMount() {
        this.fetchTables();
    }

    render() {
        const tables = this.props.tables.map((t, key) => {
            const attributes = t.attributes.map((a, key) =>
                <Keyword key={key} name={a.name} removeKeyword={()=>this.removeAttribute(t.name, a.name)} />);
            const foreignKeys = t.foreignKeys.map((f, key) =>
                <Keyword key={key} name={f.toAttribute} removeKeyword={()=>this.removeForeignKey(t.name, f.fromAttribute, f.toTable, f.toAttribute)} />);
            return <Keyword key={key} name={t.name} removeKeyword={()=>this.removeTable(t.name)}>
                <Menu top={true}>
                    <div className="database-container">
                        <Menu>
                            <h3>Attributes:</h3>
                            <KeywordEntry addKeyword={(name)=>this.addAttribute(t.name, name)}/>
                            {attributes}
                        </Menu>
                    </div>
                    <div className="database-container">
                        <Menu>
                            <h3>Foreign keys:</h3>
                            <ForeignKeyEntry addKeyword={(fromAttribute, toTable, toAttribute) =>
                                this.addForeignKey(t.name, fromAttribute, toTable, toAttribute)}
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
                <KeywordEntry addKeyword={this.addTable.bind(this)} />
                {tables}
            </div>
        );
    }
}