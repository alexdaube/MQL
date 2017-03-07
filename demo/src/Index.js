import React from 'react';
import ReactDOM from 'react-dom';
import {createStore, applyMiddleware} from "redux";
import { anchorate } from 'anchorate';
import { Provider } from "react-redux";
import ReduxPromise from "redux-promise";
import { Router, browserHistory } from 'react-router';

import reducers from "./reducers/rootReducer";
import routes from './routes';
import '../styles/app.scss';

const createStoreWithMiddleware = applyMiddleware(ReduxPromise)(createStore);

function onUpdate () {
    anchorate()
}

ReactDOM.render(
    <Provider store={createStoreWithMiddleware(reducers)}>
        <Router
            onUpdate={onUpdate}
            history={browserHistory}
            routes={routes}/>
    </Provider>,
    document.getElementById('app'));
