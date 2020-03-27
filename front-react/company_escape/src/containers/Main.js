import React , { useEffect }from 'react'
import { connect } from "react-redux";
import * as actionTypes from '../actions/index'
import moment from 'moment';
import { runDateTimeThread } from '../util/dateTimes/DateTimeService';

const mainProps = {
    date : '',
    time : '',
    handleClick : ()=>{},
    dateTimeUpdate : () =>{},
}


function Main (mainProps){

    console.log('왜 두번튐??')
    
    let date_interval = () => {
        runDateTimeThread((date)=>{
            console.log(date);
            mainProps.dateTimeUpdate(date);
        });
    }

    // 라이프 사이클 
    useEffect( () =>{
        window.addEventListener("load", date_interval);
        return () => {
            window.removeEventListener("load", date_interval);
        };
    },[]);

    return (
        <div>
            <button onClick={mainProps.handleClick}>날짜 재계산하기</button>
            <p>
                현재위치 : 회사 ( 회사 | 정자역 | 8106 버스 | 시흥환승센터 | 3200버스 | 시흥  )

                위치검색 자동유무 (자동 , 수동 )

            </p>
            <p>
                {mainProps.time}
            </p>
        </div>
    )
}


let mapStateToProps = (state) => {
    return {
        date : state.clock.date,
        time : state.clock.time
    }
}

let mapDispatchToProps = (dispatch) =>{
    return {
        handleClick : () => {
            let momentDate = moment().format(`YYYY-MM-DD hh:mm:ss`);
            dispatch(actionTypes.ClockAction.clockUpdate(momentDate))
        },
        dateTimeUpdate : (dateTime) =>{
            dispatch(actionTypes.ClockAction.clockUpdate(dateTime))
        }
    }
}



export default connect(mapStateToProps, mapDispatchToProps)(Main);