import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {HelloWorld} from './components/HelloWorld'

ReactDOM.render(<HelloWorld
        firstname="MQL"
        lastname="TEAM"/>,
    document.getElementById('app'));
