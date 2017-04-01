import React from 'react';
import {Link} from 'react-router';

import Menu from '../components/Menu';
import './Keywords.css';

export default class Keywords extends React.Component {
    render() {
        return (
            <div className="keywords-layout">
                <div className="keywords-menu">
                    <Menu>
                        <Link to="/keywords/tables"><h3><nobr>Tables</nobr></h3></Link>
                        <Link to="/keywords/operators"><h3><nobr>Operators</nobr></h3></Link>
                        <Link to="/keywords/junctions"><h3><nobr>Junctions</nobr></h3></Link>
                    </Menu>
                </div>
                <div className="keywords-children">
                    {this.props.children}
                </div>
            </div>
        );
    }
}