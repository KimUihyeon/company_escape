import axios from 'axios';

let url = 'http://openapi.gbis.go.kr/ws/rest/buslocationservice';

let defaultReqeust = {
    apiKeyParam : `serviceKey=${process.env.REACT_APP_API_KEY}`, 
}

function getUrl( param ){
    return [url , param].join('?');
}


class AxiosUtil {

    

    
    get(url){
        return axios.get(url);
    }

    post(){

    }
}

export const axiosUtil = new AxiosUtil();