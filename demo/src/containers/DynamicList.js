import React, {Component} from "react";
import {connect} from "react-redux";
import {Button, Table} from "reactstrap";
import {CSVLink} from "react-csv";
import moment from "moment";
import DynamicListHeader from "../components/DynamicListHeader";
import DynamicListBody from "../components/DynamicListBody";
import {undoCamelCasing} from "../utils/strings";
import {flattenData} from "../utils/data";

export class DynamicList extends Component {
    extractLabels() {
        return this.props.query.data[0].map(obj => {
            return undoCamelCasing(obj.name);
        });
    }

    getFlattenData() {
        return flattenData(this.props.query.data)
    }

    render() {
        if (!this.props.query.data.length) {
            return (
                <div style={{'textAlign': 'center'}} className="mqlDynamicList">
                    You haven't made a valid search!
                </div>
            );
        }
        return (
            <div>
                <Table responsive hover inverse className="mqlDynamicList">
                    <DynamicListHeader labels={this.extractLabels()}/>
                    <DynamicListBody data={this.props.query.data}/>
                </Table>
                <br/>
                <div className="text-center">
                    <CSVLink
                        data={this.getFlattenData.bind(this)()}
                        filename={`mql_result_${moment().format('YYYY_MM_DD_HH_mm_ss')}.csv`}>
                        <Button outline color="danger" size="lg">Export</Button>
                    </CSVLink>
                </div>
            </div>
        );
    }
}

const mapStateToProps = ({query}) => {
    return {query};
};

export default connect(mapStateToProps)(DynamicList);
