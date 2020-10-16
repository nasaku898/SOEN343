import React, {useState} from "react";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import LoginForm from "./components/landing/LoginForm.js";
import RegistrationForm from "./components/landing/RegistrationForm.js";
import {AuthContext} from "./context/Auth.js";
import OutputConsole from './components/OutputConsole/OutputConsole';
import SHCPanel from "./components/SHCPanel/SHCPanel";

const App = () => {
 // we will use this to get/fetch authentication token
 const [authTokens, setAuthTokens] = useState(
     localStorage.getItem("token") || ""
 );

  const [outputData, setOutputData] = useState([{date: new Date(), data: "This is a sample action log."}]);


  const appendOutputData = (data) => {
    setOutputData(outputData => [...outputData, data]);
  }
  
 const setTokens = data => {
  localStorage.setItem("token", JSON.stringify(data));
  setAuthTokens(data);
 };

 return (
     <AuthContext.Provider value={{authTokens, setAuthTokens: setTokens}}>
      <Router>
       <div>
        <h1>Welcome to the home page for our SOEN343 project!</h1>
        <Switch>
         <Route path="/register" component={RegistrationForm}/>
         <Route path="/login" component={LoginForm}/>
         
         <SHCPanel />
         
         <OutputConsole 
          outputData={outputData}
          />
        </Switch>
       </div>
      </Router>
     </AuthContext.Provider>
 );
}

export default App;
