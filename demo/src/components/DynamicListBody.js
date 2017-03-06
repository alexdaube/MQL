import React from 'react';
import guid from "../utils/guid";

const renderBody = (rowData) => {
    return (
        <tr key={guid()}>
            {Object.keys(rowData).map(key => {
                return <td key={guid()}>{rowData[key]}</td>;
            })}
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