import * as actionTypes from '../actions/ActionTypes';

const initialState = { 
    dateTime : ''
}


export const clock = (state = initialState , action) =>{

    switch(action.type){
        case actionTypes.CLOCK_UPDATE : {
            return {
                ...state,
                date : action.date,
                time : action.time
            }
        }
        default : {
            return{
                ...state
            }
        }

    }
}