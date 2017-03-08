import React from 'react';
import { Route, IndexRoute } from 'react-router';

import {App} from './views/App';
import ListView from './views/ListView';
import DocumentationView from './views/DocumentationView';
import {documentBlocksMarkup} from "./constants/demo_documentation";


export default (
    <Route path="/" component={App} >
        <IndexRoute component={ListView}/>
        <Route path="documentation" component={DocumentationView} markup={documentBlocksMarkup}/>
        <Route path="*" component={ListView} />
    </Route>
);