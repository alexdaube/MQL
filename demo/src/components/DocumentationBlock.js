import React, {Component} from 'react';

export default class DocumentationBlock extends Component  {
    renderOrderedList() {
        if (this.props.markup.orderedList) {
            return (
                <ol className="mqlDocumentationOrderedList">
                    {this.props.markup.orderedList.map((lst, i) => {
                        return <li key={i}>{lst}</li>
                    })}
                </ol>
            );
        }
    }

    renderExamples() {
        if (this.props.markup.examples) {
            return (
                <div className="docs-example">
                    {this.props.markup.examples.map((example, i) => {
                        return <span key={i}>{example}</span>
                    })}
                </div>
            );
        }
    }

    renderText() {
        return this.props.markup.text ? this.props.markup.text : '';
    }

    render() {
        return (
            <div id={this.props.markup.id} name={this.props.markup.id} className="documentationSection">
                <br/><br/>
                {this.props.markup.title}
                {this.renderText()}
                {this.renderOrderedList()}
                {this.renderExamples()}
                <br/>
            </div>
        );
    }
}