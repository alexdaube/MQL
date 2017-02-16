import * as React from 'react';
import { Route, IndexRoute } from 'react-router';

import {App} from './views/App';
import ListView from './views/ListView';

export default (
    <Route path="/" component={App} >
        <IndexRoute component={ListView}/>
        <Route path="*" component={ListView} />
    </Route>
);