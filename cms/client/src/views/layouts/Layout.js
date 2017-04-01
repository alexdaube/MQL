import React from 'react';
import {Link} from 'react-router';

import Menu from '../components/Menu';
import './Layout.css';

export default class Layout extends React.Component {
    render() {
        return (
            <div>
                <div className="layout">
                    <div>
                        <Menu className="layout-menu" top={true}>
                            <Link to="/home"><h2>Home</h2></Link>
                            <Link to="/keywords"><h2>Keywords</h2></Link>
                            <Link to="/database"><h2>Database</h2></Link>
                        </Menu>
                    </div>
                    <div className="children">
                        {this.props.children}
                    </div>
                </div>
            </div>
        );
    }
}