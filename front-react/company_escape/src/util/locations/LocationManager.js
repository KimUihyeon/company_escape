import { getLocation } from "./LocationService";
import { devConsole } from '../Util';

let serviceRunning = false;


let serviceInterval = (callback)=>{
    setInterval(()=>{
        getLocation(callback);
        serviceRunning = true;
    }, 1000 * 5);    
}

export function restoreLocationService(successFunc,errorFunc){
    let cycleCount = 0 ;
    let _fu = ()=>{}

    let lifeInterval = setInterval(()=>{
        devConsole('연결 시도중 ..  lifeInterval');

        if(cycleCount > 3){
            clearInterval(lifeInterval);
            errorFunc();
        }


        if(getLocation(successFunc) === true){
            console.log('서비스 시작');
            serviceInterval(successFunc);
            clearInterval(lifeInterval);
        }
        
        cycleCount ++;
    },2000);
}



export function startLocationService(callBack, failCallBack) {
    if(serviceRunning === true){
        clearInterval(serviceInterval);
    }

    restoreLocationService(callBack ,failCallBack);
};