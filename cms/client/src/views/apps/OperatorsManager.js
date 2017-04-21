import React from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

import './OperatorsManager.css';
import Keyword from "../components/Keyword";
import KeywordEntry from "../components/KeywordEntry";
import Menu from "../components/Menu";
import * as actions from "../../actions/operatorsAction";

@connect((store) => {
    return {
        operators: store.operators.operators
    }
}, (dispatch) => {
    return bindActionCreators({
        fetchOperators: actions.fetchOperators,
        addOperator: actions.addOperator,
        addSynonym: actions.addOperatorSynonym,
        removeOperator: actions.removeOperator,
        removeSynonym: actions.removeOperatorSynonym,
    }, dispatch);
})
export default class OperatorsManager extends React.Component {

    componentWillMount() {
        this.props.fetchOperators();
    }

    render() {
        const operators = this.props.operators.map((o, key) => {
            const synonyms = o.synonyms.map((s, key) =>
                <Keyword removeKeyword={this.props.removeSynonym.bind(this, o.name, s)} name={s} key={key}/>);
            return <Keyword removeKeyword={this.props.removeOperator.bind(this, o.name)} name={o.name} key={key}>
                <div className="operator-container">
                    <h3>Synonyms:</h3>
                    <KeywordEntry addKeyword={this.props.addSynonym.bind(this, o.name)}/>
                    {synonyms}
                </div>
            </Keyword>
        });
        return (
            <div className="operator-manager">
                <h1>Operators</h1>
                <Menu>
                    <KeywordEntry addKeyword={this.props.addOperator.bind(this)}/>
                    {operators}
                </Menu>
            </div>
        );
    }
}