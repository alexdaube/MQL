import * as React from 'react';
import { connect } from 'react-redux';
import DynamicListHeader from '../components/DynamicListHeader';
import DynamicListBody from '../components/DynamicListBody';

class DynamicList extends React.Component<any, any> {
    render() {
        console.log(this.props.query);
        if (!this.props.query) {
            return  <div style={{'textAlign': 'center'}}>You haven't made a valid search!</div>
        }

        return (
            <table className="table table-hover">
                <DynamicListHeader labels={this.props.query.attributes}/>
                <DynamicListBody data={this.props.query.data} />
            </table>
        );
    }
}

const mapStateToProps = ({ query }) => {
    return { query };
};

export default connect(mapStateToProps)(DynamicList);