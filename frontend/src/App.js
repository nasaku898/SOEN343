/* eslint-disable react/jsx-filename-extension */
import React, { useState, useEffect, useMemo } from "react";
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
import { getAuthenticatedUser } from "./modules/UserProfileList/UserService";
import { HouseContext } from "./context/CurrentHouse";
import { useUserContext } from "./context/UserContext";
import { getHouse } from "./modules/HouseOverview/HouseService";

const App = () => {
  // we will use this to get/fetch authentication token
  const [authTokens, setAuthTokens] = useState(
    localStorage.getItem("token") || ""
  );

  const [user, setUser] = useState(null);
  const [house, setHouse] = useState(null);

  const userValue = useMemo(() => ({ user, setUser }), [user, setUser]);
  const houseValue = useMemo(() => ({ house, setHouse }), [house, setHouse]);

  const setTokens = (data) => {
    localStorage.setItem("token", JSON.stringify(data));
    setAuthTokens(data);
  };

  const loadUser = async () => {
    setUser(await getAuthenticatedUser());
  };

  const loadHouse = async () => {
    setHouse(await getHouse(~~user.houseIds[0]));
  };

  //TODO this is so ugly idk what else to do to make it work tho ='[

  useEffect(() => {
    (async () => {
      loadUser();
    })();
  }, []);

  useEffect(() => {
    if (authTokens) {
      (async () => {
        await loadUser();
      })();
    }
  }, [authTokens]);

  useEffect(() => {
    if (user && authTokens) {
      (async () => {
        await loadHouse();
      })();
    }
  }, [user]);

  return (
    <AuthContext.Provider value={{ authTokens, setAuthTokens: setTokens }}>
      <useUserContext.Provider value={userValue}>
        <HouseContext.Provider value={houseValue}>
          <Router>
            <div>
              <Navbar authTokens={authTokens} />
              <Switch>
                <Route exact path="/" component={LoginForm} />
                <Route path="/register" component={RegistrationForm} />
                <Route path="/login" component={LoginForm} />
                <PrivateRoute path="/Upload" component={HouseUploadForm} />
                <ProtectedRoute
                  path="/shs"
                  component={SHSPage}
                  houseId={house}
                />
                <ProtectedRoute Route path="/shc" component={SHCPage} />
              </Switch>
            </div>
          </Router>
        </HouseContext.Provider>
      </useUserContext.Provider>
    </AuthContext.Provider>
  );
};

export default App;
