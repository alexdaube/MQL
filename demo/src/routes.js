import React from 'react';
import { Route, IndexRoute } from 'react-router';

import {App} from './views/App';
import ListView from './views/ListView';
import DocumentationView from './views/DocumentationView';

export default (
    <Route path="/" component={App} >
        <IndexRoute component={ListView}/>
        <Route path="documentation" component={DocumentationView} />
        <Route path="*" component={ListView} />
    </Route>
);