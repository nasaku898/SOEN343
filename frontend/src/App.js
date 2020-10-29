/* eslint-disable react/jsx-filename-extension */
import React, { useState } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LoginForm from "./components/landing/LoginForm";
import RegistrationForm from "./components/landing/RegistrationForm";
import { AuthContext } from "./context/Auth";
import "./App.css";
import Navbar from "./components/tabs-navbar/Navbar";
import SHSPage from "./components/landing/SHSPage";
import SHCPage from "./components/landing/SHCPage";
import PrivateRoute from "./components/routeconfig/PrivateRoute";
import ProtectedRoute from "./components/routeconfig/ProtectedRoute";
import HouseUploadForm from "./components/HouseLayout/HouseUploadForm";

const App = () => {
  // we will use this to get/fetch authentication token
  const [authTokens, setAuthTokens] = useState(
    localStorage.getItem("token") || ""
  );

  const setTokens = (data) => {
    localStorage.setItem("token", JSON.stringify(data));
    setAuthTokens(data);
  };

  return (
    <AuthContext.Provider value={{ authTokens, setAuthTokens: setTokens }}>
      <Router>
        <div>
          <Navbar authTokens={authTokens} />
          <Switch>
            <Route exact path="/" component={LoginForm} />
            <Route path="/register" component={RegistrationForm} />
            <Route path="/login" component={LoginForm} />
            <PrivateRoute path="/Upload" component={HouseUploadForm} />
            <ProtectedRoute path="/shs" component={SHSPage} />
            <ProtectedRoute Route path="/shc" component={SHCPage} />
          </Switch>
        </div>
      </Router>
    </AuthContext.Provider>
  );
};

export default App;
