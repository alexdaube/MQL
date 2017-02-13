import * as axios from 'axios';
import {FETCH_QUERY} from './types';
import {QUERY_URL} from '../constants/api_endpoints';

export function fetchQuery(query) {
    const request = axios.post(QUERY_URL, {query: query});
    return {
        type: FETCH_QUERY,
        payload: request
    };
}
