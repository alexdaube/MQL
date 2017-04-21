import React from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

import "./TablesManager.css";
import Keyword from "../components/Keyword";
import KeywordEntry from "../components/KeywordEntry";
import Menu from "../components/Menu";
import * as actions from "../../actions/tablesActions";

@connect((store) => {
    return {
        tables: store.tables.tables
    };
}, (dispatch) => {
    return bindActionCreators({
        fetchTables: actions.fetchTables,
        addTable: actions.createTable,
        addAttribute: actions.addAttribute,
        addSynonym: actions.addTableSynonym,
        addAttributeSynonym: actions.addAttributeSynonym,
        removeTable: actions.removeTable,
        removeAttribute: actions.removeAttribute,
        removeTableSynonym: actions.removeTableSynonym,
        removeAttributeSynonym: actions.removeAttributeSynonym
    }, dispatch);
})
export default class TablesManager extends React.Component {

    componentWillMount() {
        this.props.fetchTables();
    }

    render() {
        const tables = this.props.tables.map((t, key) => {
            const attributes = t.attributes.map((a, key) => {
                const synonyms = a.synonyms.map((s, key) =>
                    <Keyword key={key} name={s} removeKeyword={this.props.removeAttributeSynonym.bind(this, t.name, a.name, s)}/>);
                return <Keyword key={key} name={a.name} removeKeyword={this.props.removeAttribute.bind(this, t.name, a.name)}>
                    <div className="table-container">
                        <h3>Synonyms:</h3>
                        <KeywordEntry addKeyword={this.props.addAttributeSynonym.bind(this, t.name, a.name)}/>
                        {synonyms}
                    </div>
                </Keyword>
            });
            const synonyms = t.synonyms.map((s, key) => <Keyword key={key} name={s} removeKeyword={this.props.removeTableSynonym.bind(this, t.name, s)}/>);
            return <Keyword key={key} name={t.name} removeKeyword={this.props.removeTable.bind(this, t.name)}>
                <Menu className="table-keywords">
                    <div className="table-container">
                        <Menu>
                            <h3>Synonyms:</h3>
                            <KeywordEntry addKeyword={this.props.addSynonym.bind(this, t.name)}/>
                            {synonyms}
                        </Menu>
                    </div>
                    <div className="table-container">
                        <Menu>
                            <h3>Attributes:</h3>
                            <KeywordEntry addKeyword={this.props.addAttribute.bind(this, t.name)}/>
                            {attributes}
                        </Menu>
                    </div>
                </Menu>
            </Keyword>;
        });
        return (
            <div className="tables-manager">
                <h1>Tables</h1>
                <Menu>
                    <KeywordEntry addKeyword={this.props.addTable.bind(this)}/>
                    {tables}
                </Menu>
            </div>
        );
    }
}