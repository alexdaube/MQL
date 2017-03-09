import React, {Component} from "react";
import {connect} from "react-redux";
import {Table} from "reactstrap";
import DynamicListHeader from "../components/DynamicListHeader";
import DynamicListBody from "../components/DynamicListBody";
import {undoCamelCasing} from "../utils/strings";

export class DynamicList extends Component {
    extractLabels() {
        return Object.keys(this.props.query[0]).map(key => {
            return undoCamelCasing(key);
        });
    }

    render() {
        if (!this.props.query.length) {
            return (
                <div style={{'textAlign': 'center'}} className="mqlDynamicList">
                    You haven't made a valid search!
                </div>
            );
        }
        return (
            <Table responsive hover inverse className="mqlDynamicList">
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
