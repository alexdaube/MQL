import * as axios from 'axios';
import {FETCH_QUERY} from './types';
import {QUERY_URL} from '../constants/api_endpoints';
import {EQUIPMENT_DATA, SITE_DATA} from "../constants/hardcoded_data";


export function fetchQuery(query) {
    //const data = query.toLowerCase().includes('equip') ? EQUIPMENT_DATA : SITE_DATA;
    const request = axios.post(QUERY_URL, {query: query});
    debugger;
    return {
        type: FETCH_QUERY,
        payload: request
        //payload: data
    };
}