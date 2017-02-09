import * as React from "react";
import { guid } from '../utils/guidGenerator';

const renderBody = (rowData) => {
    return (
        <tr key={guid()}>
            {rowData.map(value => {
                return <td key={guid()}>{value}</td>;
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