import * as React from "react";
import guid from '../utils/guid';

const renderHeader = (label) => {
    return (
        <th key={guid()}>{label}</th>
    );
};

export default (props) => {
    return (
        <thead>
            <tr>
                {props.labels.map(renderHeader)}
            </tr>
        </thead>
    );
}