import React from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

import './JunctionsManager.css';
import Keyword from "../components/Keyword";
import KeywordEntry from "../components/KeywordEntry";
import Menu from "../components/Menu";
import * as actions from "../../actions/junctionsActions";

@connect((store) => {
    return {
        junctions: store.junctions.junctions
    }
}, (dispatch) => {
    return bindActionCreators({
        fetchJunctions: actions.fetchJunctions,
        addJunction: actions.addJunction,
        addSynonym: actions.addJunctionSynonym,
        removeJunction: actions.removeJunction,
        removeSynonym: actions.removeJunctionSynonym
    }, dispatch);
})
export default class JunctionsManager extends React.Component {

    componentWillMount() {
        this.props.fetchJunctions();
    }

    render() {
        const junctions = this.props.junctions.map((j, key) => {
            const synonyms = j.synonyms.map((s, key) =>
                <Keyword className="junction-synonym" removeKeyword={this.props.removeSynonym.bind(this, j.name, s)} name={s} key={key}/>);
            return <Keyword className="junction-name" removeKeyword={this.props.removeJunction.bind(this, j.name)} name={j.name} key={key}>
                <div className="junction-container">
                    <h3>Synonyms:</h3>
                    <KeywordEntry className="synonym-entry" addKeyword={this.props.addSynonym.bind(this, j.name)}/>
                    {synonyms}
                </div>
            </Keyword>
        });
        return (
            <div className="junction-manager">
                <h1>Junctions</h1>
                <Menu>
                    <KeywordEntry className="junction-entry" addKeyword={this.props.addJunction.bind(this)}/>
                    {junctions}
                </Menu>
            </div>
        );
    }
}