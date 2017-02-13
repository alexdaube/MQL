import {FETCH_QUERY} from "../actions/types";

export default function (state = [], action) {
    switch (action.type) {
        case FETCH_QUERY :
            console.log("Payload inside reducer ", action.payload);
            debugger;
            return action.payload.data;
    }
    return state;
}