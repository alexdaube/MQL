import * as React from "react";
import * as ReactDOM from "react-dom";
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import * as ReduxPromise from 'redux-promise';

import { App } from './components/app'
import reducers from './reducers/index';

const createStoreWithMiddleware = applyMiddleware(ReduxPromise)(createStore);

ReactDOM.render(

    <Provider store={createStoreWithMiddleware(reducers)}>
        <App firstname="MQL" lastname="TEAM" />
    </Provider>,
    document.getElementById('app'));
