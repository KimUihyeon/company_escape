import * as actionTypes from "./ActionTypes";

export function clockTest(){
    
}

export function clockUpdate(dateTime){
    return {
        type :actionTypes.CLOCK_UPDATE,
        date : dateTime.date,
        time : dateTime.time
    }
}