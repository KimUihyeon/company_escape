import * as actionTypes  from '../actions/ActionTypes'

const initialState = {
    
}


export const location = (state = initialState , action ) => {

    switch(action.type){
        case actionTypes.LOCATION_RELOAD: {
        }

        default : {

            return {
                ...state
            }
        }
    }
}