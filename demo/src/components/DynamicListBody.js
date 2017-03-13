import React from "react";
import guid from "../utils/guid";

const renderBody = (rowData) => {
    return (
        <tr key={guid()}>
            {rowData.map(obj => {
                return <td key={guid()}>{obj.value}</td>;
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