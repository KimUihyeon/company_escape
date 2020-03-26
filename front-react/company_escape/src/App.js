import React, { useState} from 'react';
import logo from './logo.svg';
import './App.css';
import dotenv  from 'dotenv';
import { Clock } from './components/Clock';
import { geolocated } from "react-geolocated";
import { PositionTest } from './components/PositionTest';
import { axiosUtil }  from './util/AxiosUtil';

import  { restoreLocationService , startLocationService } from './util/locations/LocationManager';
import { devConsole } from './util/Util';
var convert = require('xml-js');


dotenv.config();

function App() {  
  let callBack = (position)=>{
      let currentLat = position.coords.latitude;
      let currentLng = position.coords.longitude;
      let data = {
        currentLat,
        currentLng
      }


      devConsole(JSON.stringify(data));
  };

  let failCallBack = ()=>{
      alert('연결에 실패하였습니다.');
  }

  startLocationService(callBack, failCallBack);

  //locationTest();
  




  // let [ postion , setPosition] = useState();

  // navigator.geolocation.watchPosition(()=>{
  //     console.log('ddd')
  // })

  // navigator.geolocation.getCurrentPosition(({coords})=>{

  //   let {latitude , longitude} = coords; // 위도 경도;

  //   let data = {latitude , longitude};

  //   console.log(JSON.stringify(data));

  //   // alert(JSON.stringify(data))
  //   return {
  //     latitude ,
  //     longitude
  //   }
  // })

  // let url = 'http://openapi.gbis.go.kr/ws/rest/buslocationservice?';
  // let param = `serviceKey=${process.env.REACT_APP_API_KEY}&routeId=233000031`;
  // let fullUrl = url+param;
  // console.log(fullUrl )
  // axiosUtil.get(fullUrl).then((res)=>{
  //   var data = convert.xml2js(res.data,{compact: true, spaces: 4});
    
  //   console.log(data.response.comMsgHeader.errMsg);
  // })

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <Clock></Clock>
        {/* <PositionTest postion={postion}></PositionTest> */}
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}



// export default geolocated({
//   positionOptions: {
//       enableHighAccuracy: false,
//   },
//   userDecisionTimeout: 5000,
// })(Demo);

export default App;
