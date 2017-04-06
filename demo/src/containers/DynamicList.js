import React, {Component} from "react";
import {connect} from "react-redux";
import {Table, Button} from "reactstrap";
import converter from "json-2-csv";
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

    dataToCsv() {
        converter.json2csv(flattenData(this.props.query.data), (err, csv) =>{
            if (err) throw err;
            console.log(csv);
        });
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
            <Button color="danger" onClick={this.dataToCsv.bind(this)}>To Excel</Button>
        </div>
        );
    }
}

const mapStateToProps = ({query}) => {
    return {query};
};

export default connect(mapStateToProps)(DynamicList);
