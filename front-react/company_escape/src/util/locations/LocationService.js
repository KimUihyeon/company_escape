
import { devConsole , devAlert } from '../Util';

var GPSTimeout = 10; //init global var NOTE: I noticed that 10 gives me the quickest result but play around with this number to your own liking

//function to be called where you want the location with the callback(position)
export function getLocation(callback) {
  if (navigator.geolocation) {
    var clickedTime = (new Date()).getTime(); //get the current time
    GPSTimeout = 10; //reset the timeout just in case you call it more then once
    ensurePosition(callback, clickedTime); //call recursive function to get position
  }
  return true;
}

//recursive position function
function ensurePosition(callback, timestamp) {
  if (GPSTimeout < 6000) {
    //call the geolocation function
    navigator.geolocation.getCurrentPosition(
      function(position) //on success
      {
        //if the timestamp that is returned minus the time that was set when called is greater then 0 the position is up to date
        if (position.timestamp - timestamp >= 0) {
          GPSTimeout = 10; //reset timeout just in case
          callback(position); //call the callback function you created
        } else //the gps that was returned is not current and needs to be refreshed
        {
          GPSTimeout += GPSTimeout; //increase the timeout by itself n*2
          ensurePosition(callback, timestamp); //call itself to refresh
        }
      },
      function(err) //error: gps failed so we will try again
      {
        devConsole(err.message);
        GPSTimeout += GPSTimeout; //increase the timeout by itself n*2
        ensurePosition(callback, timestamp); //call itself to try again
      }, {
        maximumAge: 0,
        timeout: GPSTimeout
      }
    )
  }
}