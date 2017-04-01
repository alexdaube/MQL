import React from 'react';
import {connect} from 'react-redux';

import Keyword from '../components/Keyword';
import KeywordEntry from '../components/KeywordEntry';
import Menu from '../components/Menu';
import {addOperator, addOperatorSynonym, removeOperator, removeOperatorSynonym, fetchOperators} from "../../actions/operatorsAction";

@connect((store) => {
    return {
        operators: store.operators.operators
    }
})
export default class OperatorsManager extends React.Component {

    componentWillMount() {
        this.props.dispatch(fetchOperators())
    }

    addOperator(name) {
        this.props.dispatch(addOperator(name));
    }

    addSynonym(operatorName, synonym) {
        this.props.dispatch(addOperatorSynonym(operatorName, synonym));
    }

    removeOperator(name) {
        this.props.dispatch(removeOperator(name));
    }

    removeSynonym(operatorName, synonym) {
        this.props.dispatch(removeOperatorSynonym(operatorName, synonym));
    }

    render() {
        const operators = this.props.operators.map((o, key) => {
            const synonyms = o.synonyms.map((s, key) =>
                <Keyword removeKeyword={()=>this.removeSynonym(o.name, s)} name={s} key={key} />);
            return <Keyword removeKeyword={()=>this.removeOperator(o.name)} name={o.name} key={key}>
                <h3>Synonyms:</h3>
                <KeywordEntry addKeyword={(name)=>this.addSynonym(o.name, name)} />
                {synonyms}
            </Keyword>
        });
        return (
            <div className="operators">
                <h1>Operators</h1>
                <Menu>
                    <KeywordEntry addKeyword={this.addOperator.bind(this)} />
                    {operators}
                </Menu>
            </div>
        );
    }
}