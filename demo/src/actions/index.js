import axios from "axios";
import * as Types  from "./types";
import {BASE_URL, QUERY_PATH} from "../constants/api_endpoints";

const fetchQueryError = (error) => {
    return {
        type: Types.FETCH_QUERY_ERROR,
        error
    };
};

const fetchQuerySuccess = (payload) => {
    return {
        type: Types.FETCH_QUERY_SUCCESS,
        payload
    };
};

const fetchQueryRequest = () => {
    return {
        type: Types.FETCH_QUERY_REQUEST
    }
};

export const fetchQuery = (query) => {
    return (dispatch) => {
        dispatch(fetchQueryRequest());
        return axios.post(`${BASE_URL}${QUERY_PATH}`, {query})
            .then(response => {
                dispatch(fetchQuerySuccess(response.data));
            })
            .catch(error => {
                dispatch(fetchQueryError(error.response.data.errorMessage));
            });
    };
};

const fetchSuggestionError = (error) => {
    return {
        type: Types.FETCH_SUGGESTIONS_ERROR,
        error
    };
};

const fetchSuggestionSuccess = (payload) => {
    return {
        type: Types.FETCH_SUGGESTIONS_SUCCESS,
        payload
    };
};

const fetchSuggestionRequest = () => {
    return {
        type: Types.FETCH_SUGGESTIONS_REQUEST
    }
};

export const fetchSuggestion = (query) => {
    return (dispatch) => {
        dispatch(fetchSuggestionRequest());
        return axios.post(`${BASE_URL}${QUERY_PATH}`, {query})
            .then(response => {
                dispatch(fetchSuggestionSuccess(response.data));
            })
            .catch(error => {
                dispatch(fetchSuggestionError(error.response.data.errorMessage));
            });
    };
};
