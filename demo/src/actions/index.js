import axios from "axios";
import {FETCH_QUERY_ERROR, FETCH_QUERY_SUCCESS, FETCH_QUERY_REQUEST} from "./types";
import {BASE_URL, QUERY_PATH} from "../constants/api_endpoints";

const fetchQueryError = (error) => {
    return {
        type: FETCH_QUERY_ERROR,
        error
    };
};

const fetchQuerySuccess = (payload) => {
    return {
        type: FETCH_QUERY_SUCCESS,
        payload
    };
};

const fetchQueryRequest = () => {
    return {
        type: FETCH_QUERY_REQUEST
    }
};

export function fetchQuery(query) {
    return (dispatch) => {
        dispatch(fetchQueryRequest());
        return axios.post(`${BASE_URL}${QUERY_PATH}`, {query: query})
            .then(response => {
                dispatch(fetchQuerySuccess(response.data));
            })
            .catch(error => {
                dispatch(fetchQueryError(error.response.data.errorMessage));
            });
    };
}
