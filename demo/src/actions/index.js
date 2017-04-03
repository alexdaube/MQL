import axios from "axios";
import * as types  from "./types";
import {BASE_URL, QUERY_PATH, SUGGESTIONS_PATH} from "../constants/api_endpoints";
import {lowerCaseInput, isInputEmpty} from "../utils/strings";

const fetchQueryError = (error) => {
    return {
        type: types.FETCH_QUERY_ERROR,
        error
    };
};

const fetchQuerySuccess = (payload) => {
    return {
        type: types.FETCH_QUERY_SUCCESS,
        payload
    };
};

const fetchQueryRequest = () => {
    return {
        type: types.FETCH_QUERY_REQUEST
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
        type: types.FETCH_SUGGESTIONS_ERROR,
        error
    };
};

const fetchSuggestionSuccess = (payload) => {
    return {
        type: types.FETCH_SUGGESTIONS_SUCCESS,
        payload
    };
};

const fetchSuggestionRequest = () => {
    return {
        type: types.FETCH_SUGGESTIONS_REQUEST
    }
};

export const fetchSuggestions = ({value}) => {
    if (isInputEmpty(value)) {
        return clearSuggestions();
    }

    return (dispatch) => {
        dispatch(fetchSuggestionRequest());
        return axios.post(`${BASE_URL}${SUGGESTIONS_PATH}`, {query: lowerCaseInput(value)})
            .then(response => {
                dispatch(fetchSuggestionSuccess(response.data));
            })
            .catch(error => {
                debugger;
                dispatch(fetchSuggestionError(error.response.data.errorMessage));
            });
    };
};

export const clearSuggestions = () => {
    return {
        type: types.CLEAR_SUGGESTIONS
    }
};
