import React from "react";
import {connect} from "react-redux";

import TableModifier from "./TableModifier";
import Keyword from '../components/Keyword';
import Menu from '../components/Menu';

@connect((store) => {
    return {
        tables: store.tables.tables
    };
})
export default class DatabaseManager extends TableModifier {

    render() {
        const tables = this.props.tables.map((t, key) => {
            const attributes = t.attributes.map((a, key) =>
                <Keyword key={key} name={a.name} removeKeyword={()=>this.removeAttribute(t.name, a.name)} />);
            const foreignKeys = t.foreignKeys.map((f, key) =>
                <Keyword key={key} name={f.toAttribute} removeKeyword={()=>this.removeForeignKey(t.name, f.fromAttribute, f.toTable, f.toAttribute)} />);
            return <Keyword key={key} name={t.name} removeKeyword={()=>this.removeTable(t.name)}>
                <Menu top={true}>
                    <Menu>
                        <h3>Attributes:</h3>
                        {attributes}
                    </Menu>
                    <Menu>
                        <h3>Foreign keys:</h3>
                        {foreignKeys}
                    </Menu>
                </Menu>
            </Keyword>
        });
        return (
            <div>
                <h1>Database</h1>
                {tables}
            </div>
        );
    }
}