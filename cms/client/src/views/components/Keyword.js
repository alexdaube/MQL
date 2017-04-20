import React from "react";
import "./Keyword.css";

export default class Keyword extends React.Component {

    constructor() {
        super(...arguments);
        this.state = {
            active: false
        };
    }

    clicked() {
        this.setState({...this.state, active: !this.state.active});
    }

    render() {
        const {children} = this.props;
        let iconClasses = "glyphicon glyphicon-plus";
        let childClasses = "keyword-children";
        let buttonClasses = "btn";
        if (this.state.active) {
            iconClasses = "glyphicon glyphicon-minus";
            childClasses += " keyword-active"
        }
        if (React.Children.count(children) === 0) {
            buttonClasses = "";
            iconClasses = "";
        }
        return (
            <div className={this.props.className}>
                <div className="keyword-container">
                    <div className={"keyword-button " + buttonClasses} onClick={this.clicked.bind(this)}>
                        <span className={"keyword-icon " + iconClasses}/>
                        <h4 className="keyword-name">{this.props.name}</h4>
                        <div className="keyword-close">
                            <a className="btn" onClick={this.props.removeKeyword.bind(this)}>✖</a>
                        </div>
                    </div>
                    <div className={childClasses}>
                        {this.props.children}
                    </div>
                </div>
            </div>
        );
    }
}