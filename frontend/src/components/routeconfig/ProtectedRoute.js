import { Redirect, Route } from "react-router-dom";
import { useAuth } from "../../context/Auth";
import React from "react";
import { useCurrentHouse } from "../../context/CurrentHouse";

const ProtectedRoute = ({ component: Component, ...rest }, houseId) => {
  const { authTokens } = useAuth();
  const { house } = useCurrentHouse();
  return (
    <Route
      {...rest}
      render={(props) =>
        authTokens !== "undefined" ? (
          house ? (
            <Component {...props} />
          ) : (
            <Redirect to={{ pathname: "/upload" }} />
          )
        ) : (
          <Redirect to={{ pathname: "/login" }} />
        )
      }
    />
  );
};

export default ProtectedRoute;
