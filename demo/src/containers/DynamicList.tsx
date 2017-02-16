import * as React from "react";
import {connect} from "react-redux";
import { Table } from 'reactstrap';
import DynamicListHeader from "../components/DynamicListHeader";
import DynamicListBody from "../components/DynamicListBody";
import {undoCamelCasing} from '../utils/strings';

class DynamicList extends React.Component<any, any> {
    extractLabels() {
        return Object.keys(this.props.query[0]).map(key => {
            return undoCamelCasing(key);
        });
    }

    render() {
        if (!this.props.query.length) {
            return (
                <div style={{'textAlign': 'center'}}>
                    You haven't made a valid search!
                </div>
            );
        }
        return (
            <Table responsive hover inverse>
                <DynamicListHeader labels={this.extractLabels()}/>
                <DynamicListBody data={this.props.query}/>
            </Table>
        );
    }
}

const mapStateToProps = ({query}) => {
    return {query};
};

export default connect(mapStateToProps)(DynamicList);
