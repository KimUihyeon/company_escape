import React from 'react';
import logo from './logo.svg';
import './App.css';
import dotenv  from 'dotenv';

dotenv.config();


function App() {

  const configString = process.env.REACT_APP_CONFIG_TEST;
  console.log(process.env)
  
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          
          {configString}
        </p>
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

export default App;
