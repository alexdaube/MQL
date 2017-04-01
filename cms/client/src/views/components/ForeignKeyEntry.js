import React from 'react';

export default class ForeignKeyEntry extends React.Component {
    constructor() {
        super(...arguments);
        this.state = {
            fromAttribute: "",
            toTable: "",
            toAttribute: "",
            tables: []
        }
    }

    submit(event) {
        if (this.state.name.length !== 0) {
            this.props.addKeyword(this.state.name);
            this.setState({...this.state, fromAttribute: "", toTable: "", toAttribute: ""});
        }
        event.preventDefault();
    }

    render() {
        let fromAttributeOptions, toTableOptions, toAttributeOptions;
        if (this.state.fromAttribute.length !== 0 && !this.state.tables.find(t => t.attributes.find(a => a.name === this.state.fromAttribute))) {
            fromAttributeOptions = this.state.tables.map(t => t.attributes.map(a => {

            }));
        }
        return (
            <form className="form-inline" onSubmit={this.submit.bind(this)}>
                <label>
                    <input className="form-control" type="text" placeholder="Name" value={this.state.name}
                           onChange={this.handleChange.bind(this)}/>
                </label>
                <input className="btn btn-primary" type="submit" value="Add"/>
            </form>
        );
    }

}