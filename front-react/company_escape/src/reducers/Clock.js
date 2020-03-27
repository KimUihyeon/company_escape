import * as actionTypes from '../actions/ActionTypes';

const initialState = { 

}


export const clock = (state = initialState , action) =>{

    switch(action.type){
        case actionTypes.CLOCK_UPDATE : {
            return {
                ...state,
                dateTime : action.dateTime,
            }
        }
        default : {
            return{
                ...state
            }
        }

    }
}