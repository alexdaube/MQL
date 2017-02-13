import * as React from "react";
import {connect} from "react-redux";
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
        console.log(this.props.query);
        if (!this.props.query.length) {
            return (
                <div style={{'textAlign': 'center'}}>
                    You haven't made a valid search!
                </div>
            );
        }
        return (
            <table className="table table-hover">
                <DynamicListHeader labels={this.extractLabels()}/>
                <DynamicListBody data={this.props.query}/>
            </table>
        );
    }
}

const mapStateToProps = ({query}) => {
    return {query};
};

export default connect(mapStateToProps)(DynamicList);