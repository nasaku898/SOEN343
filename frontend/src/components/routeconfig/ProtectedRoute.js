import { Redirect, Route } from "react-router-dom";
import { useAuth } from "../../context/Auth";
import React from "react";

const ProtectedRoute = ({ component: Component, ...rest }, houseId) => {
  const { authTokens } = useAuth();
  return (
    <Route
      {...rest}
      render={(props) =>
        authTokens !== "undefined" ? (
          houseId ? (
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
