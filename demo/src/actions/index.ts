import { EQUIPMENT_DATA, SITE_DATA } from '../constants/hardcoded_data'

export const FETCH_QUERY = 'FETCH_QUERY';

export function fetchQuery(keyword) {
    const data = keyword.toLowerCase().includes('equip') ? EQUIPMENT_DATA : SITE_DATA;
    return {
        type: FETCH_QUERY,
        payload: data
    };
}