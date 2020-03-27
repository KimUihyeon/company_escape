const moment = require('moment');
const stringSplitor = '$$$';

export function getNowDate(){
    let momentDate = moment().format(`YYYY-MM-DD${stringSplitor}hh:mm:ss`);
    let [date , time] = momentDate.split(stringSplitor);

    let dateObj = {
        date,
        time
    }
    return dateObj;
}

export function runDateTimeThread(callback){
    callback(getNowDate());
    setInterval(()=>{
        callback(getNowDate());
    }, 1000);
}
