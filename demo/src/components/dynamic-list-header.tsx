import * as React from 'react';

const renderHeader = (label) => {
    return (
        <th key={label}>{label}</th>
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