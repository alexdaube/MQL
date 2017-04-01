import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, browserHistory} from 'react-router';
import {Provider} from 'react-redux';

import './index.css';
import store from './store';
import Layout from "./views/layouts/Layout";
import Home from "./views/apps/Home";
import Keywords from "./views/apps/Keywords";
import TablesManager from './views/apps/TablesManager';
import OperatorsManager from "./views/apps/OperatorsManager";
import JunctionsManager from "./views/apps/JunctionsManager";
import DatabaseManager from "./views/apps/DatabaseManager";

ReactDOM.render((
    <Provider store={store}>
        <Router history={browserHistory}>
            <Route path="/" component={Layout}>
                <IndexRoute component={Home}/>
                <Route path="home" component={Home}/>
                <Route path="keywords" component={Keywords}>
                    <Route path="tables" component={TablesManager}/>
                    <Route path="operators" component={OperatorsManager}/>
                    <Route path="junctions" component={JunctionsManager}/>
                </Route>
                <Route path="database" component={DatabaseManager} />
            </Route>
        </Router>
    </Provider>
), document.getElementById('root'));
