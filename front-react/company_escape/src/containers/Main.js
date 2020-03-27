import React from 'react'
import { connect } from "react-redux";
import * as actionTypes from '../actions/index'
import moment from 'moment';

const mainProps = {
    dateTime : '',
    handleClick : ()=>{}
}

function Main (mainProps){

    return (
        <div>
            <button onClick={mainProps.handleClick}>날짜 재계산하기</button>
            <p>
                {mainProps.dateTime}
            </p>
        </div>
    )
}


let mapStateToProps = (state) => {
    return {
        dateTime : state.clock.dateTime
    }
}

let mapDispatchToProps = (dispatch) =>{
    return {
        handleClick : () => {
            let momentDate = moment().format(`YYYY-MM-DD hh:mm:ss`);
            dispatch(actionTypes.ClockAction.clockUpdate(momentDate))
        }
    }
}



export default connect(mapStateToProps, mapDispatchToProps)(Main);