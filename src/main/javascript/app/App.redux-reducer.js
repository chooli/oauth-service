import { actionTypes } from './App.redux-actions'

const clientDetailsReducer = ( state = {}, action )=> {

    const newState = {
        ...state
    }

    switch (action.type) {
        case actionTypes.SHOW_CLIENT_DETAILS:
            return action.clientDetails;
        case actionTypes.CHANGE_CLIENT_ID:
            return {
                ...state,
                clientId: action.clientId
            };
        default: return newState;
    }
}

export {
    clientDetailsReducer
}
