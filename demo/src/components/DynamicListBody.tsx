import * as React from 'react';


function guid() {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    }
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
        s4() + '-' + s4() + s4() + s4();
}

const renderBody = (rowData) => {
    return (
        <tr key={guid()}>
            {rowData.map(value => {return <td key={guid()}>{value}</td>;})}
        </tr>
    );
};

export default (props) => {
    return (
        <tbody>
            {props.data.map(renderBody)}
        </tbody>
    );
}