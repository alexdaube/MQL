import React from "react";
import {connect} from "react-redux";

import './JunctionsManager.css';
import Keyword from "../components/Keyword";
import KeywordEntry from "../components/KeywordEntry";
import Menu from "../components/Menu";
import {
    addJunction,
    addJunctionSynonym,
    removeJunction,
    removeJunctionSynonym,
    fetchJunctions
} from "../../actions/junctionsAction";

@connect((store) => {
    return {
        junctions: store.junctions.junctions
    }
})
export default class JunctionsManager extends React.Component {

    componentWillMount() {
        this.props.dispatch(fetchJunctions());
    }

    addJunction(name) {
        this.props.dispatch(addJunction(name));
    }

    addSynonym(junctionName, synonym) {
        this.props.dispatch(addJunctionSynonym(junctionName, synonym));
    }

    removeJunction(name) {
        this.props.dispatch(removeJunction(name));
    }

    removeSynonym(junctionName, synonym) {
        this.props.dispatch(removeJunctionSynonym(junctionName, synonym));
    }

    render() {
        const junctions = this.props.junctions.map((j, key) => {
            const synonyms = j.synonyms.map((s, key) =>
                <Keyword removeKeyword={() => this.removeSynonym(j.name, s)} name={s} key={key}/>);
            return <Keyword removeKeyword={() => this.removeJunction(j.name)} name={j.name} key={key}>
                <div className="junction-container">
                    <h3>Synonyms:</h3>
                    <KeywordEntry addKeyword={(name) => this.addSynonym(j.name, name)}/>
                    {synonyms}
                </div>
            </Keyword>
        });
        return (
            <div className="junction-manager">
                <h1>Junctions</h1>
                <Menu>
                    <KeywordEntry addKeyword={this.addJunction.bind(this)}/>
                    {junctions}
                </Menu>
            </div>
        );
    }
}