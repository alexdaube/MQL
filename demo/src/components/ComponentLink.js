import React from 'react';
import {Link} from "react-router";
import {NavItem, NavLink} from "reactstrap";


export default (props) => {
    const isActive = props.item.id === props.activeItem ? 'active' : '';
    return (
        <NavItem className="componentLink">
            <NavLink tag={Link}
                     to={`${props.toBaseUrl}${props.item.id}`}
                     activeClassName={isActive}
                     onClick={props.onActiveChange }>
                {props.item.menuTitle}
            </NavLink>
        </NavItem>
    );
};
