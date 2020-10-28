import React, {useState} from "react";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import LoginForm from "./components/landing/LoginForm.js";
import RegistrationForm from "./components/landing/RegistrationForm.js";
import {AuthContext} from "./context/Auth.js";
import './App.css';
import Navbar from './components/tabs-navbar/Navbar';
import SHSPage from "./components/landing/SHSPage.js";
import SHCPage from "./components/landing/SHCPage.js";
import {PrivateRoute} from "./components/routeconfig/PrivateRoute";

const App = () => {
  // we will use this to get/fetch authentication token
  const [authTokens, setAuthTokens] = useState(
    localStorage.getItem("token") || ""
  );

  const setTokens = data => {
    localStorage.setItem("token", JSON.stringify(data));
    setAuthTokens(data);
  };

  return (
    <AuthContext.Provider value={{ authTokens, setAuthTokens: setTokens }}>
      {
        <Router>
          <div>
            <Navbar authTokens={authTokens}/>
            <Switch>
              <Route exact path="/" component={LoginForm}/>
              <Route path="/register" component={RegistrationForm}/>
              <Route path="/login" component={LoginForm}/>
              <PrivateRoute path="/shs" component={SHSPage}/>
              <PrivateRoute Route path="/shc" component={SHCPage}/>
            </Switch>
          </div>
        </Router>
      }
    </AuthContext.Provider>
  );
}

export default App;
