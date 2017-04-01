import React from "react";

export default class Menu extends React.Component {
    constructor() {
        super(...arguments);
        this.state = {
            activeItem: -1
        };
        this.classes = "inactive";
        this.activeClasses = "active";
    }

    activeItem(id) {
        this.setState({activeItem: id});
    }

    render() {
        const {children} = this.props;
        const items = React.Children.map(children, (child, id) => {
            const active = this.state.activeItem === id ? this.activeClasses : this.classes;
            return <li key={id} className={"menu-item " + active}
                       onClick={this.activeItem.bind(this, id)}>{children[id]}</li>
        });
        let menuClasses = "nav nav-pills nav-stacked";
        if (this.props.top) {
            menuClasses = "nav nav-tabs nav-justified";
        }

        return (
            <div>
                <ul className={menuClasses}>
                    {items}
                </ul>
            </div>
        );
    }
}