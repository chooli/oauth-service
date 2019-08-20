import { actionTypes } from './App.redux-actions'

const clientDetailsReducer = ( state = {}, action )=> {

    const newState = {
        ...state
    }

    switch (action.type) {
        case actionTypes.CHANGE_CLIENT_ID:
            return {
                ...state,
                clientId: action.clientId
            };
        default: return newState;
    }
}

const userReducer = ( state = {}, action )=> {

    const newState = {
        ...state
    }

    switch (action.type) {
        case actionTypes.CHANGE_USERNAME:
            return {
                ...state,
                username: action.username
            };
        default: return newState;
    }
}

export {
    clientDetailsReducer,
    userReducer
}
