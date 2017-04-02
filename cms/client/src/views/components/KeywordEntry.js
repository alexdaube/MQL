import React from "react";

export default class KeywordEntry extends React.Component {

    constructor() {
        super(...arguments);
        this.state = {
            name: ""
        };
    }

    handleChange(event) {
        this.setState({...this.state, name: event.target.value});
        event.preventDefault();
    }

    submit(event) {
        if (this.state.name.length !== 0) {
            this.props.addKeyword(this.state.name);
            this.setState({...this.state, name: ""});
        }
        event.preventDefault();
    }

    render() {
        return (
            <form className="form-inline" onSubmit={this.submit.bind(this)}>
                <input className="form-control" type="text" placeholder="Name" value={this.state.name}
                       onChange={this.handleChange.bind(this)}/>
                <input className="btn btn-primary" type="submit" value="Add"/>
            </form>
        );
    }
}