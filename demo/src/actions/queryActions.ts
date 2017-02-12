import * as axios from 'axios';

export const FETCH_QUERY = 'FETCH_QUERY';

export function fetchQuery(keyword) {
    const request = axios.get(`localhost:8000/query/${keyword}`);
    return {
        type: FETCH_QUERY,
        payload: request
    };
}