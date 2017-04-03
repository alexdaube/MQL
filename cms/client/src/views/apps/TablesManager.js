import React from "react";
import {connect} from "react-redux";
import "./TablesManager.css";
import Keyword from "../components/Keyword";
import KeywordEntry from "../components/KeywordEntry";
import Menu from "../components/Menu";
import TableModifier from "./TableModifier";

@connect((store) => {
    return {
        tables: store.tables.tables
    };
})
export default class TablesManager extends TableModifier {

    componentWillMount() {
        this.fetchTables();
    }

    render() {
        const tables = this.props.tables.map((t, key) => {
            const attributes = t.attributes.map((a, key) => {
                const synonyms = a.synonyms.map((s, key) =>
                    <Keyword key={key} name={s} removeKeyword={() => this.removeAttributeSynonym(t.name, a.name, s)}/>);
                return <Keyword key={key} name={a.name} removeKeyword={() => this.removeAttribute(t.name, a.name)}>
                    <div className="table-container">
                        <h3>Synonyms:</h3>
                        <KeywordEntry addKeyword={(name) => this.addAttributeSynonym(t.name, a.name, name)}/>
                        {synonyms}
                    </div>
                </Keyword>
            });
            const synonyms = t.synonyms.map((s, key) => <Keyword key={key} name={s}
                                                                 removeKeyword={() => this.removeTableSynonym(t.name, s)}/>);
            return <Keyword key={key} name={t.name} removeKeyword={() => this.removeTable(t.name)}>
                <Menu className="table-keywords" top={true}>
                    <div className="table-container">
                        <Menu>
                            <h3>Synonyms:</h3>
                            <KeywordEntry addKeyword={(name) => this.addSynonym(t.name, name)}/>
                            {synonyms}
                        </Menu>
                    </div>
                    <div className="table-container">
                        <Menu>
                            <h3>Attributes:</h3>
                            <KeywordEntry addKeyword={(name) => this.addAttribute(t.name, name)}/>
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
                    <KeywordEntry addKeyword={this.addTable.bind(this)}/>
                    {tables}
                </Menu>
            </div>
        );
    }
}